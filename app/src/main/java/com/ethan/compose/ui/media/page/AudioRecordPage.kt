package com.ethan.compose.ui.media.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.StatusBarsView

@Composable
@Preview
fun AudioRecordPage() {
    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("录音", true)


    }
}