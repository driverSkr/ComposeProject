package com.ethan.compose.ui.custom.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.ui.custom.view.StatusBarsView

/**
 * 图片对比动画
 */
@Composable
@Preview
fun ImageComparePage() {

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("图片对比动画")
    }
}