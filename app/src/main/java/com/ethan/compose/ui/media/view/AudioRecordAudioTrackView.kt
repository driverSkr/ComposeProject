package com.ethan.compose.ui.media.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.theme.White

@Composable
@Preview
fun AudioRecordAudioTrackView() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AudioRecord 录音 + AudioTrack  播放",
            color = White,
        )

        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "录制",
                color = White,
            )
        }

        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "暂停",
                color = White,
            )
        }

        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "播放",
                color = White,
            )
        }

        Button(onClick = { /*TODO*/ }) {
            Text(
                text = "保存",
                color = White,
            )
        }
    }
}