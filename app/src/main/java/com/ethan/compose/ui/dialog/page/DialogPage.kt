package com.ethan.compose.ui.dialog.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.widget.rememberConfirmDialog

@Composable
@Preview
fun DialogPage() {
    val dialog = rememberConfirmDialog(
        title = "标题",
        content = "你好，弹窗！",
        mainTv = "确认",
        secondaryTv = "取消",
        mainBtn = { /*TODO*/ },
        secondaryBtn = {})

    Box(modifier = Modifier.statusBarsPadding().fillMaxSize()) {
        Button(onClick = { dialog.value = true }) {
            Text(text = "确认弹窗")
        }
    }
}