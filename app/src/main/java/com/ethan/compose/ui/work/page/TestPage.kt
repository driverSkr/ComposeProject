package com.ethan.compose.ui.work.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.ui.custom.view.StatusBarsView

@Composable
@Preview
fun TestPage() {

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("测试代码", true)

    }
}