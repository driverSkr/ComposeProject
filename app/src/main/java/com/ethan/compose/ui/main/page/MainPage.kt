package com.ethan.compose.ui.main.page

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.ui.component.ComponentActivity
import com.ethan.compose.ui.composite.CompositeActivity
import com.ethan.compose.ui.custom.CustomActivity
import com.ethan.compose.ui.custom.model.CardItem
import com.ethan.compose.ui.custom.view.ListCardView
import com.ethan.compose.ui.custom.view.StatusBarsView
import com.ethan.compose.ui.dialog.DialogActivity
import com.ethan.compose.ui.media.MediaActivity
import com.ethan.compose.ui.room.RoomActivity
import com.ethan.compose.ui.technique.TechniqueActivity
import com.ethan.compose.ui.work.TestActivity

/**
 * 首页
 */
@Composable
@Preview
fun MainPage() {
    val context = LocalContext.current
    val items = listOf(
        CardItem("基础组件", true) { ComponentActivity.launch(context) },
        CardItem("组合组件", true) { CompositeActivity.launch(context) },
        CardItem("自定义组件", true) { CustomActivity.launch(context) },
        CardItem("弹窗组件", true) { DialogActivity.launch(context) },
        CardItem("多媒体组件", true) { MediaActivity.launch(context) },
        CardItem("Room数据库", true) { RoomActivity.launch(context) },
        CardItem("技术、技巧、知识", true) { TechniqueActivity.launch(context) },
        CardItem("Test", true) { TestActivity.launch(context) },
    )

    BackHandler {  }
    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("主页", false)
        ListCardView(items)
    }
}