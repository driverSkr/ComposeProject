package com.ethan.compose.ui.main.page

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.ui.component.ComponentActivity
import com.ethan.compose.ui.custom.CustomActivity
import com.ethan.compose.ui.dialog.DialogActivity
import com.ethan.compose.ui.media.AudioRecordActivity
import com.ethan.compose.work.TestActivity

@Composable
@Preview
fun MainPage() {
    val context = LocalContext.current
    //aspectRatio设置宽高比
    BackHandler {  }
    Column {
        StatusBarsView("主页", false)
        Button(onClick = {
            ComponentActivity.launch(context)
        }) {
            Text(text = "基础组件")
        }

        Button(onClick = {
            DialogActivity.launch(context)
        }) {
            Text(text = "弹窗组件")
        }

        Button(onClick = {
            CustomActivity.launch(context)
        }) {
            Text(text = "自定义组件")
        }

        Button(onClick = {
            AudioRecordActivity.launch(context)
        }) {
            Text(text = "多媒体组件")
        }

        Button(onClick = {
            context.startActivity(Intent(context, TestActivity::class.java))
        }) {
            Text(text = "Test")
        }
    }
}