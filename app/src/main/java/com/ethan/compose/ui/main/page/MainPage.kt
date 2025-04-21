package com.ethan.compose.ui.main.page

import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.ListCardView
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.ui.component.ComponentActivity
import com.ethan.compose.ui.custom.CustomActivity
import com.ethan.compose.ui.dialog.DialogActivity
import com.ethan.compose.ui.media.AudioRecordActivity
import com.ethan.compose.ui.swipe.SwipeActivity
import com.ethan.compose.work.TestActivity

@Composable
@Preview
fun MainPage() {
    val context = LocalContext.current
    val items = listOf(
        Triple("基础组件", true) { ComponentActivity.launch(context) },
        Triple("弹窗组件", true) { DialogActivity.launch(context) },
        Triple("自定义组件", true) { CustomActivity.launch(context) },
        Triple("多媒体组件", true) { AudioRecordActivity.launch(context) },
        Triple("侧滑组件", true) { SwipeActivity.launch(context) },
        Triple("Test", true) { context.startActivity(Intent(context, TestActivity::class.java)) },
    )

    //aspectRatio设置宽高比
    BackHandler {  }
    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("主页", false)
        ListCardView(items)
    }
}