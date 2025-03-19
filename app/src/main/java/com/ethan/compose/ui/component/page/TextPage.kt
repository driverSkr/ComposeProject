package com.ethan.compose.ui.component.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.compose.custom.widget.rememberConfirmDialog

@Composable
@Preview
/**
 * Text 对应View中的 TextView
 */
fun TextPage() {
    val dialog = rememberConfirmDialog(
        title = "标题",
        content = "你好，弹窗！",
        mainTv = "确认",
        secondaryTv = "取消",
        mainBtn = { /*TODO*/ },
        secondaryBtn = {})

    Column(modifier = Modifier.fillMaxSize().padding(all = 10.dp)) {
        Text(
            text = "This is Text",
            color = Color.Blue,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { dialog.value = true }) {}
    }
}