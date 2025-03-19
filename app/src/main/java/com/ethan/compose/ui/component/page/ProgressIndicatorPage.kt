package com.ethan.compose.ui.component.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun ProgressIndicatorPage() {
    Column(modifier = Modifier.padding(all = 10.dp)) {
        Text(text = "圆形进度条")
        CircularProgressIndicator(
            color = Color.Green,
            strokeWidth = 6.dp
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "长条形进度条")
        LinearProgressIndicator(
            color = Color.Blue,
            trackColor = Color.Gray
        )
    }
}