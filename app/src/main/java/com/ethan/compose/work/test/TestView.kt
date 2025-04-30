package com.ethan.compose.work.test

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.ListCardView
import com.ethan.compose.custom.view.StatusBarsView

@Composable
@Preview
fun TestView() {
    val showView = remember { mutableIntStateOf(0) }
    val items = listOf(
        Triple("侧滑删除组件", false) { showView.intValue = 1 },
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("测试代码", true)
        ListCardView(items = items)

        AnimatedContent(showView.intValue, label = "") {
            when(it) {
                1 -> SwipeToDismissView()
            }
        }
    }
}