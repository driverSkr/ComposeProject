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
import com.ethan.compose.work.TabWithSubViews

@Composable
@Preview
fun TestView() {
    val showView = remember { mutableIntStateOf(0) }
    val items = listOf(
        Triple("TabWithSubViews", false) { showView.intValue = 0 },
        Triple("侧滑删除组件", false) { showView.intValue = 1 },
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("测试代码", true)
        ListCardView(items = items)

        AnimatedContent(showView.intValue, label = "") {
            when(it) {
                0 -> TabWithSubViews()
                1 -> SwipeToDismissView()
                else -> TabWithSubViews()
            }
        }
    }
}