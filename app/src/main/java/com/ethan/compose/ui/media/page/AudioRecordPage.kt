package com.ethan.compose.ui.media.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.theme.Black
import com.ethan.compose.ui.media.view.RecordView

@Composable
@Preview
fun AudioRecordPage() {



    Column(modifier = Modifier.fillMaxSize().background(color = Black)) {
        StatusBarsView("录音", true)

        RecordView()
    }
}