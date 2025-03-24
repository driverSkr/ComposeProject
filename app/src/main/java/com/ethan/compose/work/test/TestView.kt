package com.ethan.compose.work.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.theme.Red

@Composable
@Preview
fun TestView() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(80.dp)
        .background(
            color = Red,
            shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
        )
    )
}