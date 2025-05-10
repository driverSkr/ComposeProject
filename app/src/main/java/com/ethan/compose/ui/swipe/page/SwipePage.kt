package com.ethan.compose.ui.swipe.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.ui.custom.view.ListCardView
import com.ethan.compose.ui.custom.view.StatusBarsView
import com.ethan.compose.ui.swipe.view.SwipeableView

@Composable
@Preview
fun SwipePage() {
    val isSelected = remember { mutableStateOf(false) }
    val items = listOf(
        Triple("SwipeToDismissBox", false) { },
        Triple("SwipeToDismiss", false) { },
        Triple("swipeable修饰符", false) { }
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("侧滑组件", true)
        ListCardView(items)

        Spacer(modifier = Modifier.height(20.dp))
        SwipeableView(isSelected.value) { isSelected.value = !isSelected.value }
    }
}