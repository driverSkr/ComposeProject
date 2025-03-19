package com.ethan.compose.ui.component.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.theme.Black10
import com.ethan.compose.theme.Purple40
import com.ethan.compose.theme.White
import kotlinx.coroutines.launch

@Composable
@Preview
fun ComponentPage() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(initialPage = 0) { 5 }
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    val pageList = listOf("Text", "Button", "Image", "ProgressIndicator", "TextField")

    LaunchedEffect(pagerState.currentPage) {
        if (pagerState.currentPage >= 0 && pagerState.currentPage < pagerState.pageCount) {
            selectedTabIndex = pagerState.currentPage
        }
    }

    Column(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        StatusBarsView("基础组件")

        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp, // 设置边缘内边距
            containerColor = Color.Transparent, // 设置透明背景以便自定义每个Tab的背景
            indicator = {}, // 去掉底部指示器
            divider = {}, // 去掉底部分隔线
        ) {

            pageList.forEachIndexed { index, pageName ->
                Tab(selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        scope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                        .then(
                            when (index) {
                                0 -> {
                                    // 第一个标签页只应用右边的内边距
                                    Modifier.padding(end = 8.dp)
                                }
                                pageList.size - 1 -> {
                                    // 最后一个标签页只应用左边的内边距
                                    Modifier.padding(start = 8.dp)
                                }
                                else -> {
                                    // 中间的标签页应用两边的内边距
                                    Modifier.padding(horizontal = 8.dp)
                                }
                            }
                        )
                        .clip(shape = RoundedCornerShape(48.dp)) // 圆角
                        .background(
                            if (selectedTabIndex == index) Purple40 else Black10
                        ) // 背景颜色
                        .padding(vertical = 4.dp, horizontal = 8.dp), // 内部填充
                ) {
                    Text(
                        text = pageName,
                        color = if (selectedTabIndex == index) White else Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }

        HorizontalPager(pagerState, beyondViewportPageCount = 2) {
            when (it) {
                0 -> TextPage()
                1 -> ButtonPage()
                2 -> ImagePage()
                3 -> ProgressIndicatorPage()
                4 -> TextFieldPage()
            }
        }
    }
}