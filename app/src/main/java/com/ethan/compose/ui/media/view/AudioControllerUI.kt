package com.ethan.compose.ui.media.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.ethan.compose.theme.White
import com.ethan.compose.utils.AudioRecordHelper
import java.io.File

//todo 测试代码
@Composable
fun AudioControllerUI(controller: AudioRecordHelper) {
    val context = LocalContext.current
    // 输出文件
    val outputFile = remember {
        File(context.externalCacheDir, "/final_recording_${System.currentTimeMillis()}.m4a")
    }
    Column {
        // 播放控制按钮
        Row {
            when (controller.recordState) {
                AudioRecordHelper.RecordState.IDLE -> {
                    Button(onClick = { context.externalCacheDir?.let { controller.startRecording(context, it) } }) {
                        Text("开始/恢复录音")
                    }
                }
                AudioRecordHelper.RecordState.RECORDING -> {
                    Button(onClick = { context.externalCacheDir?.let{ controller.pauseRecording(it) } }) {
                        Text("暂停录音")
                    }
                    Button(onClick = { controller.stopRecording(outputFile){} }) {
                        Text("停止录音")
                    }
                }
                AudioRecordHelper.RecordState.PAUSED -> {
                    Button(onClick = { context.externalCacheDir?.let { controller.startRecording(context, it) } }) {
                        Text("开始/恢复录音")
                    }
                    Button(onClick = { controller.stopRecording(outputFile){} }) {
                        Text("停止录音")
                    }
                }
            }
        }

        Row {
            when (controller.playState) {
                AudioRecordHelper.PlayState.IDLE -> {
                    Button(onClick = { controller.startPlaying() }) {
                        Text("播放音频")
                    }
                }
                AudioRecordHelper.PlayState.PLAYING -> {
                    Button(onClick = { controller.pausePlaying() }) {
                        Text("暂停播放")
                    }
                    Button(onClick = { controller.stopPlaying() }) {
                        Text("停止播放")
                    }
                }
                AudioRecordHelper.PlayState.PAUSED -> {
                    Button(onClick = { controller.resumePlaying() }) {
                        Text("恢复播放")
                    }
                    Button(onClick = { controller.stopPlaying() }) {
                        Text("停止播放")
                    }
                }
            }
        }

        // 进度条
        // Slider(
        //     value = controller.currentPlayPositionMs.toFloat(),
        //     onValueChange = { controller.seekTo(it.toLong()) },
        //     valueRange = 0f..controller.currentDurationMs.toFloat()
        // )

        Text("录制时长：" + formatTime(controller.currentDurationMs), color = White)
        Text("播放进度：" + formatTime(controller.currentPlayPositionMs), color = White)
    }
}

@SuppressLint("DefaultLocale")
private fun formatTime(milliseconds: Long): String {
    val seconds = (milliseconds / 1000) % 60
    val minutes = (milliseconds / (1000 * 60)) % 60
    return String.format("%02d:%02d", minutes, seconds)
}