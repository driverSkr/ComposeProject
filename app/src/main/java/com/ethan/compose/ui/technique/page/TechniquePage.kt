package com.ethan.compose.ui.technique.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.ui.custom.view.StatusBarsView

/**
 * 工作中学到的技巧、技术、知识合集
 */
@Composable
@Preview
fun TechniquePage() {
    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("技术、技巧、知识", true)
    }
}