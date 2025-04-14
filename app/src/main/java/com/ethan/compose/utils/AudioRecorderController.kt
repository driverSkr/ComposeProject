package com.ethan.compose.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaCodec
import android.media.MediaCodec.BufferInfo
import android.media.MediaCodecInfo
import android.media.MediaFormat
import android.media.MediaMuxer
import android.media.MediaRecorder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.RandomAccessFile
import java.nio.ByteBuffer

/**
 * AudioRecord 录音 + AudioTrack  播放
 */
class AudioRecorderController {

    // 状态变量
    var recordState by mutableStateOf(RecordState.IDLE)
        private set
    var playState by mutableStateOf(PlayState.IDLE)
        private set
    var currentDurationMs by mutableLongStateOf(0L)
        private set
    var currentPlayPositionMs by mutableLongStateOf(0L)
        private set

    enum class RecordState { IDLE, RECORDING, PAUSED }
    enum class PlayState { IDLE, PLAYING, PAUSED }

    // 音频参数配置
    companion object {
        private const val SAMPLE_RATE = 44100
        private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
        private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
        private const val BUFFER_SIZE_FACTOR = 4  // 缓冲区倍数
    }

    // Audio Components
    private var audioRecord: AudioRecord? = null
    private var audioTrack: AudioTrack? = null
    private var outputStream: FileOutputStream? = null
    private var pcmFile: File? = null

    // 分段录音管理
    private val tempPcmFiles = mutableListOf<File>()
    private var isFinalizing = false

    // 协程管理
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var recordJob: Job? = null
    private var playJob: Job? = null

    /** 开始\恢复录音 */
    fun startRecording(context: Context, outputDir: File) {
        if (recordState == RecordState.RECORDING) return

        // 检查录音权限
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Log.w("AudioRecorder", "Missing RECORD_AUDIO permission")
            return
        }

        try {
            // 初始化文件
            if (recordState == RecordState.IDLE) {
                initNewRecordingSession(outputDir)
            }

            // 配置 AudioRecord
            val bufferSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, CHANNEL_CONFIG, AUDIO_FORMAT)
                .coerceAtLeast(1024) * BUFFER_SIZE_FACTOR

            audioRecord = AudioRecord(
                MediaRecorder.AudioSource.MIC,
                SAMPLE_RATE,
                CHANNEL_CONFIG,
                AUDIO_FORMAT,
                bufferSize
            ).apply { startRecording() }

            // 启动录音协程
            recordJob = scope.launch {
                val buffer = ByteArray(bufferSize)
                outputStream = FileOutputStream(pcmFile, true).also { stream ->
                    while (isActive && recordState == RecordState.RECORDING) {
                        val bytesRead = audioRecord!!.read(buffer, 0, bufferSize)
                        if (bytesRead > 0) {
                            withContext(Dispatchers.IO) {
                                stream.write(buffer, 0, bytesRead)
                            }
                            updateDuration(bytesRead)
                        }
                    }
                    stream.flush()
                }
            }

            recordState = RecordState.RECORDING
        } catch (e: Exception) {
            Log.e("AudioRecorder", "Start recording failed", e)
            handleRecordingError()
        }
    }

    /** 暂停录音 */
    fun pauseRecording() {
        if (recordState != RecordState.RECORDING) return

        recordState = RecordState.PAUSED
        recordJob?.cancel()
        audioRecord?.stop()
        outputStream?.flush()
        tempPcmFiles.add(pcmFile!!) // 保存当前分段
        pcmFile = null
    }

    /** 停止录音并编码 */
    fun stopRecording(outputFile: File, callback: (Boolean) -> Unit) {
        if (recordState == RecordState.IDLE) return

        cleanupRecordingResources()

        scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    mergeAndEncode(tempPcmFiles, outputFile).also {
                        tempPcmFiles.forEach { it.delete() }
                    }
                }
                callback(true)
            } catch (e: Exception) {
                Log.e("AudioRecorder", "Encoding failed", e)
                callback(false)
            }
        }
    }

    /** 播放音频 */
    fun startPlaying() {
        if (playState == PlayState.PLAYING || pcmFile == null) return

        val bufferSize = AudioTrack.getMinBufferSize(
            SAMPLE_RATE,
            AudioFormat.CHANNEL_OUT_MONO,
            AudioFormat.ENCODING_PCM_16BIT
        )

        audioTrack = AudioTrack(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build(),
            AudioFormat.Builder()
                .setSampleRate(SAMPLE_RATE)
                .setChannelMask(AudioFormat.CHANNEL_OUT_MONO)
                .setEncoding(AudioFormat.ENCODING_PCM_16BIT)
                .build(),
            bufferSize,
            AudioTrack.MODE_STREAM,
            AudioManager.AUDIO_SESSION_ID_GENERATE
        ).apply { play() }

        playState = PlayState.PLAYING

        playJob = scope.launch {
            FileInputStream(pcmFile).use { fis ->
                val buffer = ByteArray(bufferSize)
                while (isActive && playState == PlayState.PLAYING) {
                    val bytesRead = fis.read(buffer)
                    if (bytesRead > 0) {
                        audioTrack?.write(buffer, 0, bytesRead)
                        updatePlayPosition(bytesRead)
                    }
                }
            }
            stopPlaying()
        }
    }

    /** 停播音频 */
    fun pausePlaying() {
        if (playState != PlayState.PLAYING) return

        playState = PlayState.PAUSED
        audioTrack?.pause()
        playJob?.cancel() // 停止数据读取协程
    }

    /** Seek 定位 */
    fun seekTo(positionMs: Long) {
        if (pcmFile == null) return

        scope.launch {
            val bytePosition = (positionMs * SAMPLE_RATE * 2 / 1000).coerceIn(0, pcmFile!!.length())

            RandomAccessFile(pcmFile, "r").use { raf ->
                raf.seek(bytePosition)
                val buffer = ByteArray(raf.length().toInt() - bytePosition.toInt())
                raf.readFully(buffer)

                withContext(Dispatchers.Main) {
                    // 重置播放
                    stopPlaying()
                    audioTrack?.write(buffer, 0, buffer.size)
                    currentPlayPositionMs = positionMs
                }
            }
        }
    }

    /** 停止播放 */
    fun stopPlaying() {
        playState = PlayState.IDLE
        playJob?.cancel()
        audioTrack?.apply {
            stop()
            release()
        }
        audioTrack = null
        currentPlayPositionMs = 0
    }

    /** 释放所有资源 */
    fun release() {
        scope.cancel()
        stopRecording(File("")) { _ -> } // 清理临时文件
        stopPlaying()
    }

    private fun initNewRecordingSession(outputDir: File) {
        pcmFile = File(outputDir, "rec_${System.currentTimeMillis()}.pcm").apply {
            createNewFile()
        }
        tempPcmFiles.clear()
        currentDurationMs = 0
    }

    private fun updateDuration(bytesRead: Int) {
        currentDurationMs += bytesRead * 1000L / (2 * SAMPLE_RATE)
    }

    private fun updatePlayPosition(bytesRead: Int) {
        currentPlayPositionMs += bytesRead * 1000L / (2 * SAMPLE_RATE)
    }

    private fun cleanupRecordingResources() {
        recordState = RecordState.IDLE
        isFinalizing = true

        recordJob?.cancel()
        audioRecord?.apply {
            stop()
            release()
        }
        outputStream?.close()

        audioRecord = null
        outputStream = null
    }

    private fun handleRecordingError() {
        scope.launch {
            withContext(Dispatchers.IO) {
                tempPcmFiles.forEach { it.delete() }
            }
            cleanupRecordingResources()
        }
    }

    /** 合并分段并编码 */
    private fun mergeAndEncode(inputFiles: List<File>, outputFile: File) {
        val muxer = MediaMuxer(outputFile.path, MediaMuxer.OutputFormat.MUXER_OUTPUT_MPEG_4)
        var trackIndex = -1
        var muxerStarted = false

        // 配置 MediaCodec
        val codec = MediaCodec.createEncoderByType(MediaFormat.MIMETYPE_AUDIO_AAC).apply {
            val format = MediaFormat.createAudioFormat(
                MediaFormat.MIMETYPE_AUDIO_AAC,
                SAMPLE_RATE,
                1
            ).apply {
                setInteger(MediaFormat.KEY_BIT_RATE, 128000)
                setInteger(MediaFormat.KEY_AAC_PROFILE, MediaCodecInfo.CodecProfileLevel.AACObjectLC)
                setInteger(MediaFormat.KEY_MAX_INPUT_SIZE, 1024 * 1024) // 1MB buffer
            }
            configure(format, null, null, MediaCodec.CONFIGURE_FLAG_ENCODE)
            start()
        }

        try {
            val bufferInfo = BufferInfo()
            var totalBytesRead = 0L

            // 处理每个 PCM 文件
            inputFiles.forEach { file ->
                FileInputStream(file).channel.use { channel ->
                    val buffer = ByteBuffer.allocateDirect(1024 * 1024)

                    while (channel.read(buffer) != -1) {
                        buffer.flip()

                        // 编码输入
                        val inputBufferIndex = codec.dequeueInputBuffer(10000)
                        if (inputBufferIndex >= 0) {
                            val inputBuffer = codec.getInputBuffer(inputBufferIndex)!!
                            inputBuffer.put(buffer)
                            codec.queueInputBuffer(
                                inputBufferIndex,
                                0,
                                buffer.limit(),
                                totalBytesRead * 1000000L / (2 * SAMPLE_RATE), // 计算时间戳
                                0
                            )
                            totalBytesRead += buffer.limit()
                        }
                        buffer.clear()

                        // 处理编码输出
                        while (true) {
                            val outputBufferIndex = codec.dequeueOutputBuffer(bufferInfo, 10000)
                            when {
                                outputBufferIndex == MediaCodec.INFO_TRY_AGAIN_LATER -> break
                                outputBufferIndex == MediaCodec.INFO_OUTPUT_FORMAT_CHANGED -> {
                                    trackIndex = muxer.addTrack(codec.outputFormat)
                                    muxer.start()
                                    muxerStarted = true
                                }
                                outputBufferIndex >= 0 -> {
                                    val outputBuffer = codec.getOutputBuffer(outputBufferIndex)!!
                                    if (muxerStarted) {
                                        muxer.writeSampleData(trackIndex, outputBuffer, bufferInfo)
                                    }
                                    codec.releaseOutputBuffer(outputBufferIndex, false)
                                }
                            }
                        }
                    }
                }
            }

            // 结束编码
            codec.signalEndOfInputStream()
            while (true) {
                val outputBufferIndex = codec.dequeueOutputBuffer(bufferInfo, 10000)
                if (outputBufferIndex == MediaCodec.INFO_TRY_AGAIN_LATER) break
                if (outputBufferIndex >= 0) {
                    codec.releaseOutputBuffer(outputBufferIndex, false)
                }
            }
        } finally {
            codec.stop()
            codec.release()
            if (muxerStarted) {
                muxer.stop()
            }
            muxer.release()
        }
    }
}