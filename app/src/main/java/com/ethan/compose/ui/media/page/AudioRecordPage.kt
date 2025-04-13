package com.ethan.compose.ui.media.page

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.theme.Black
import com.ethan.compose.ui.media.view.AudioRecordAudioTrackView
import com.ethan.compose.ui.media.view.MediaRecorderExoPlayerView
import com.ethan.compose.ui.media.view.RecordView

@Composable
@Preview
fun AudioRecordPage() {

    var selectPage by remember { mutableStateOf(AudioRecordPageEnum.MediaRecorderMediaPlayer) }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Black)) {
        StatusBarsView("录音", true)

        Button(onClick = { selectPage = AudioRecordPageEnum.MediaRecorderMediaPlayer }) {
            Text(text = "MediaRecorder 录音 + MediaPlayer  播放")
        }
        Button(onClick = { selectPage = AudioRecordPageEnum.MediaRecorderExoPlayer }) {
            Text(text = "MediaRecorder 录音 + ExoPlayer  播放")
        }
        Button(onClick = { selectPage = AudioRecordPageEnum.AudioRecordAudioTrack }) {
            Text(text = "AudioRecord 录音 + AudioTrack  播放")
        }

        AnimatedContent(selectPage, label = "") {
            when(it) {
                AudioRecordPageEnum.MediaRecorderMediaPlayer -> RecordView()
                AudioRecordPageEnum.MediaRecorderExoPlayer -> MediaRecorderExoPlayerView()
                AudioRecordPageEnum.AudioRecordAudioTrack -> AudioRecordAudioTrackView()
            }
        }
    }
}

enum class AudioRecordPageEnum {
    /**
     * MediaRecorder 录音
     * MediaPlayer  播放
     */
    MediaRecorderMediaPlayer,

    /**
     * MediaRecorder 录音
     * ExoPlayer  播放
     */
    MediaRecorderExoPlayer,

    /**
     * AudioRecord 录音（采集PCM数据，支持暂停时缓存数据到内存/文件
     * AudioTrack 播放（直接播放PCM数据）
     */
    AudioRecordAudioTrack,
}