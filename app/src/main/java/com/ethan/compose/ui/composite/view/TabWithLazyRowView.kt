//package com.ethan.compose.ui.composite.view
//
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.itemsIndexed
//import androidx.compose.foundation.lazy.rememberLazyListState
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.ScrollableTabRow
//import androidx.compose.material3.Tab
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import com.blankj.utilcode.util.ScreenUtils
//import com.blankj.utilcode.util.SizeUtils
//import com.ethan.compose.theme.NO_PADDING_TEXT_STYLE
//import com.ethan.compose.theme.Purple291E3D
//import com.ethan.compose.theme.PurpleBC97FF
//import com.ethan.compose.theme.Transparent
//import com.ethan.compose.theme.White
//import com.ethan.compose.work.tabDataSource
//import kotlinx.coroutines.launch
//
//@Composable
//@Preview
//fun TabWithLazyRowView() {
//    var selectedTabIndex by remember { mutableIntStateOf(0) }
//    var selectedMaterialIndex by remember { mutableIntStateOf(-2) }
//    val allTextItems = tabDataSource.flatMap { it.textItems }
//
//    val itemWidths = remember { mutableStateListOf<Int>() }
//    val screenWidthPx = with(LocalDensity.current) { ScreenUtils.getScreenWidth() - SizeUtils.dp2px(16f) }
//    val scope = rememberCoroutineScope()
//    val listState = rememberLazyListState()
//
//    Column {
//        ScrollableTabRow(
//            selectedTabIndex = selectedTabIndex,
//            edgePadding = 16.dp, // 设置边缘内边距
//            containerColor = Transparent, // 设置透明背景以便自定义每个Tab的背景
//            indicator = {}, // 去掉底部指示器
//            divider = {}, // 去掉底部分隔线
//        ) {
//            tabDataSource.forEachIndexed { index, data ->
//                Tab(
//                    selected = selectedTabIndex == index,
//                    onClick = {
//                        selectedTabIndex = index
//
//                        // 找到当前标签页的第一个 TextItem 在 allTextItems 中的索引
//                        allDetails?.let {
//                            val startIndex = allDetails!!.indexOfFirst {
//                                // it.title?.key_name == data.title?.key_name
//                                data.detail?.contains(it) == true
//                            }
//                            if (startIndex != -1) {
////                                selectedMaterialIndex = startIndex
//                                scope.launch {
//                                    val scrollToIndex = if (startIndex == 0) startIndex else startIndex + 1
//                                    listState.scrollToItem(scrollToIndex)
//                                }
//                            }
//                        }
//                    },
//                    modifier = Modifier
//                        .wrapContentSize()
//                        .padding(end = 8.dp)
//                        .background(color = if (selectedTabIndex == index) Purple291E3D else Transparent, shape = RoundedCornerShape(20.dp)),
//                ) {
//                    Text(
//                        text = data.title,
//                        color = if (selectedTabIndex == index) PurpleBC97FF else White,
//                        fontSize = 12.sp,
//                        style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400),
//                        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
//                    )
//                }
//            }
//        }
//
//        Spacer(modifier = Modifier.height(16.dp))
//
//        allDetails?.let { details ->
//            LazyRow(
//                state = listState,
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(horizontal = 16.dp),
//                horizontalArrangement = Arrangement.spacedBy(8.dp)
//            ) {
//                item {
//                    VideoSelectView(selectedMaterialIndex == -1) {
//
//                    }
//                }
//                itemsIndexed(details) { index, detail ->
//                    val isload = remember { mutableStateOf(false) }
//                    RemoveItem(index == selectedMaterialIndex, isload, detail, {
//
//                    })
//                }
//            }
//        }
//    }
//}