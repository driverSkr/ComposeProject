package com.ethan.compose.utils

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.media.AudioAttributes
import android.media.AudioFormat
import android.media.AudioManager
import android.media.AudioRecord
import android.media.AudioTrack
import android.media.MediaRecorder
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.app.ActivityCompat
import com.ethan.videoediting.AudioCutting
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
import kotlin.math.min

class AudioRecordHelper {

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

    private var audioRecord: AudioRecord? = null
    private var audioTrack: AudioTrack? = null
    private var outputStream: FileOutputStream? = null

    // PCM 缓存
    private var currentPcmFile: File? = null
    // 存储所有录制的 PCM 分段文件
    private val recordedPcmSegments = mutableListOf<File>()
    // 存储合并后的 PCM 文件（每次暂停后生成）
    private var mergedPcmFile: File? = null

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)
    private var recordJob: Job? = null
    private var playJob: Job? = null


    // 音频参数配置
    companion object {
        private const val SAMPLE_RATE = 44100
        private const val CHANNEL_CONFIG = AudioFormat.CHANNEL_IN_MONO
        private const val AUDIO_FORMAT = AudioFormat.ENCODING_PCM_16BIT
        private const val BUFFER_SIZE_FACTOR = 4  // 缓冲区倍数
    }

    /** 开始\恢复录音 */
    fun startRecording(context: Context, outputDir: File) {
        if (recordState == RecordState.RECORDING) return

        // 检查录音权限
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Log.w("AudioRecorder", "Missing RECORD_AUDIO permission")
            return
        }

        try {
            // 创建新的 PCM 分段文件
            currentPcmFile = File(outputDir, "segment_${System.currentTimeMillis()}.pcm").apply {
                createNewFile()
            }
            // 如果是首次开始（不是恢复），清空记录
            if (recordState == RecordState.IDLE) {
                recordedPcmSegments.clear()
                mergedPcmFile = null
                currentDurationMs = 0L
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
                outputStream = FileOutputStream(currentPcmFile).also { stream ->
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
            cleanupRecordingResources()
        }
    }

    /** 暂停录音（合并所有已录制的 PCM 分段） */
    fun pauseRecording(outputDir: File) {
        if (recordState != RecordState.RECORDING) return

        recordState = RecordState.PAUSED
        recordJob?.cancel()
        audioRecord?.stop()
        outputStream?.flush()
        outputStream?.close()

        // 将当前分段添加到列表
        currentPcmFile?.let {
            recordedPcmSegments.add(it)
            currentPcmFile = null
        }
        // 合并所有 PCM 分段
        scope.launch {
            try {
                mergedPcmFile = mergePcmSegments(outputDir)
                Log.d("AudioRecorder", "PCM segments merged to: ${mergedPcmFile?.path}")
            } catch (e: Exception) {
                Log.e("AudioRecorder", "Merge PCM segments failed", e)
            }
        }
    }

    /** 停止录音并编码为 M4A */
    fun stopRecording(outputFile: File, callback: (Boolean) -> Unit) {
        if (recordState == RecordState.IDLE) return

        // 如果是正在录制中停止，先添加当前分段
        if (recordState == RecordState.RECORDING) {
            currentPcmFile?.let {
                recordedPcmSegments.add(it)
                currentPcmFile = null
            }
        }

        cleanupRecordingResources()

        scope.launch {
            try {
                withContext(Dispatchers.IO) {
                    // 如果有暂停过，使用合并后的文件；否则合并所有分段
                    val finalPcmFile = mergePcmSegments(outputFile.parentFile!!)

                    // 将 PCM 编码为 M4A
                    val result = AudioCutting.encodePcmToM4a(finalPcmFile, outputFile)
                    // 清理临时文件
                    recordedPcmSegments.forEach { it.delete() }
                    finalPcmFile?.delete()
                    mergedPcmFile?.delete()
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
        if (playState == PlayState.PLAYING || mergedPcmFile == null) return

        startPlayingFromPosition(0) // 从头开始播放
    }

    /** 暂停播放 */
    fun pausePlaying() {
        if (playState != PlayState.PLAYING) return

        playState = PlayState.PAUSED
        Log.d("ethan","pausePlaying 播放状态：${playState.name}")
        audioTrack?.pause()
        playJob?.cancel() // 停止数据读取协程
    }

    /** 恢复播放 */
    fun resumePlaying() {
        if (playState != PlayState.PAUSED || mergedPcmFile == null) return

        // 从上次位置继续播放
        startPlayingFromPosition(currentPlayPositionMs)
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

    /** 从指定位置开始播放 */
    private fun startPlayingFromPosition(startPositionMs: Long) {
        if (mergedPcmFile == null) return

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
            try {
                FileInputStream(mergedPcmFile).use { fis ->
                    // 计算并跳过已播放的字节
                    val bytesToSkip = (startPositionMs * SAMPLE_RATE * 2 / 1000)
                    fis.channel.position(bytesToSkip)

                    val buffer = ByteArray(bufferSize)
                    while (isActive && playState == PlayState.PLAYING) {
                        val bytesRead = fis.read(buffer)
                        if (bytesRead == -1) break // 文件结束
                        if (bytesRead > 0) {
                            audioTrack?.write(buffer, 0, bytesRead)
                            updatePlayPosition(bytesRead)
                        }
                    }
                }
            } catch (e: Exception) {
                Log.e("AudioPlayer", "Playback error", e)
            } finally {
                stopPlaying()
            }
        }
    }

    /** 合并所有 PCM 分段文件 */
    private suspend fun mergePcmSegments(outputDir: File): File? {
        return withContext(Dispatchers.IO) {
            try {
                val mergedFile = File(outputDir, "merged_${System.currentTimeMillis()}.pcm").apply {
                    createNewFile()
                }

                FileOutputStream(mergedFile).use { output ->
                    recordedPcmSegments.forEach { segment ->
                        FileInputStream(segment).use { input ->
                            input.channel.transferTo(0, segment.length(), output.channel)
                        }
                    }
                }
                mergedFile
            } catch (e: Exception) {
                Log.e("AudioRecorder", "Merge failed", e)
                null
            }
        }
    }

    private fun updateDuration(bytesRead: Int) {
        currentDurationMs += bytesRead * 1000L / (2 * SAMPLE_RATE)
    }

    /** 更新播放位置（需要重置计算方式） */
    private fun updatePlayPosition(bytesRead: Int) {
        currentPlayPositionMs += bytesRead * 1000L / (2 * SAMPLE_RATE)
        // 确保不超过总时长
        currentPlayPositionMs = min(currentPlayPositionMs, currentDurationMs)
    }

    private fun cleanupRecordingResources() {
        recordState = RecordState.IDLE

        recordJob?.cancel()
        audioRecord?.apply {
            stop()
            release()
        }
        outputStream?.close()

        audioRecord = null
        outputStream = null
    }
}