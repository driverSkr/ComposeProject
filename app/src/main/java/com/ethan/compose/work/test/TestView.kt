package com.ethan.compose.work.test

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.theme.Black
import com.ethan.compose.theme.White
import com.ethan.compose.ui.main.page.ItemView
import com.ethan.compose.utils.antiShakeClick
import com.ethan.compose.work.TabWithSubViews

@Composable
@Preview
fun TestView() {
    val showView = remember { mutableIntStateOf(0) }

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("测试代码", true)
        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = White, contentColor = Black, disabledContainerColor = White, disabledContentColor = Black),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 12.dp),
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                ItemView("TabWithSubViews", modifier = Modifier.antiShakeClick { showView.intValue = 0 })
                ItemView("侧滑删除组件", modifier = Modifier.antiShakeClick { showView.intValue = 1 })
            }
        }

        AnimatedContent(showView.intValue, label = "") {
            when(it) {
                0 -> TabWithSubViews()
                1 -> SwipeToDismissView()
                else -> TabWithSubViews()
            }
        }
    }
}