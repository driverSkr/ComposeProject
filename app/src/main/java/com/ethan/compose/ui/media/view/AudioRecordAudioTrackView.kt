package com.ethan.compose.ui.media.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.theme.White
import com.ethan.compose.utils.AudioRecordHelper

@Composable
@Preview
fun AudioRecordAudioTrackView() {

    val audioRecordHelper by remember { mutableStateOf(AudioRecordHelper()) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "AudioRecord 录音 + AudioTrack  播放",
            color = White,
        )

//        AudioControllerUI(audioRecordHelper)
        AudioRecord()
    }
}