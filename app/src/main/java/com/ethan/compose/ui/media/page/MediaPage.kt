package com.ethan.compose.ui.media.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.ListCardView
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.ui.media.AudioRecordActivity

@Composable
@Preview
fun MediaPage() {
    val context = LocalContext.current
    val items = listOf(
        Triple("音频录制、裁剪、播放", true) { AudioRecordActivity.launch(context) },
        Triple("视频录制、裁剪、播放", true) {  }
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("多媒体")

        ListCardView(items)
    }
}