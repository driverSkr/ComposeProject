package com.ethan.compose.ui.technique.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.ui.custom.model.CardItem
import com.ethan.compose.ui.custom.view.ListCardView
import com.ethan.compose.ui.custom.view.StatusBarsView

/**
 * 工作中学到的技巧、技术、知识合集
 */
@Composable
@Preview
fun TechniquePage() {

    val items = listOf(
        CardItem("Pair和Triple的使用", true, isCompleted = false),
        CardItem("aspectRatio设置宽高比", true, isCompleted = false),
        CardItem("状态同步的几种实现方式", true, isCompleted = false),
        CardItem("分页请求的实现", true, isCompleted = false),
        CardItem("权限请求方式", true, isCompleted = false),
        CardItem("高斯模糊效果的实现", true, isCompleted = false),
        CardItem("多语言设置及语言切换", true, isCompleted = false),
        CardItem("谷歌登录、支付组件", true, isCompleted = false),
        CardItem("支付宝、微信支付组件", true, isCompleted = false),
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("技术、技巧、知识", true)

        ListCardView(items = items)
    }
}