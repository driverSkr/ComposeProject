package com.ethan.compose.work

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
@Preview
fun TabWithSubViews() {
    // 状态变量用于跟踪当前选中的标签页索引
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val allTextItems = tabDataSource.flatMap { it.textItems }
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    // 监听 LazyRow 的滚动位置，并根据滚动位置更新选中的 Tab
    LaunchedEffect(lazyListState) {
        snapshotFlow { lazyListState.firstVisibleItemIndex }
            .distinctUntilChanged()
            .collect { firstVisibleItemIndex ->
                if (firstVisibleItemIndex != -1) {
                    val visibleItem = allTextItems[firstVisibleItemIndex]
                    val newTabIndex = tabDataSource.indexOfFirst {
                        it.textItems.contains(visibleItem)
                    }
                    if (newTabIndex != -1 && newTabIndex != selectedTabIndex) {
                        selectedTabIndex = newTabIndex
                    }
                }
            }
    }

    Column {
        // ScrollableTabRow 显示标签页标题，并去掉底部指示器和分隔线
        ScrollableTabRow(
            selectedTabIndex = selectedTabIndex,
            edgePadding = 16.dp, // 设置边缘内边距
            containerColor = Color.Transparent, // 设置透明背景以便自定义每个Tab的背景
            indicator = {}, // 去掉底部指示器
            divider = {}, // 去掉底部分隔线
        ) {
            tabDataSource.forEachIndexed { index, tabData ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = {
                        selectedTabIndex = index
                        // 找到当前标签页的第一个 TextItem 在 allTextItems 中的索引
                        val startIndex = allTextItems.indexOfFirst {
                            tabData.textItems.contains(it)
                        }
                        if (startIndex != -1) {
                            scope.launch {
                                lazyListState.scrollToItem(startIndex)
                            }
                        }
                    },
                    modifier = Modifier
                        .then(
                            if (index == 0) {
                                // 第一个标签页只应用右边的内边距
                                Modifier.padding(end = 8.dp)
                            } else if (index == tabDataSource.size - 1) {
                                // 最后一个标签页只应用左边的内边距
                                Modifier.padding(start = 8.dp)
                            } else {
                                // 中间的标签页应用两边的内边距
                                Modifier.padding(horizontal = 8.dp)
                            }
                        )
                        .clip(shape = RoundedCornerShape(20.dp)) // 圆角
                        .background(
                            if (selectedTabIndex == index) Color.Cyan else Color.Gray
                        ) // 背景颜色
                        .padding(8.dp), // 内部填充
                ) {
                    Text(
                        text = tabData.title,
                        color = if (selectedTabIndex == index) Color.White else Color.Black,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }
            }
        }



        // 根据当前选中的标签页索引显示相应的子视图
        LazyRow(
            state = lazyListState,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            itemsIndexed(allTextItems) { index, textItem ->
                SubView(textItem)
            }
        }
    }
}

@Composable
fun SubView(textItem: TextItem) {
    Box(modifier = Modifier
        .background(color = Color.LightGray, shape = RoundedCornerShape(8.dp))
        .padding(8.dp)) {
        Text(
            text = textItem.content,
            fontSize = 14.sp,
            modifier = Modifier.padding(vertical = 4.dp)
        )
    }
}