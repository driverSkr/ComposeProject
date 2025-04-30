package com.ethan.compose.ui.composite.page

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.custom.view.ListCardView
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.theme.Black_24252C
import com.ethan.compose.ui.component.page.ComponentPage
import com.ethan.compose.ui.composite.view.TabWithLazyRow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CompositePage() {
    val scope = rememberCoroutineScope()
    val showView = remember { mutableIntStateOf(0) }
    val dialog = BottomDialog(showView.intValue)

    val items = listOf(
        Triple("ScrollableTabRow和HorizontalPager联动", false) {
            showView.intValue = 0
            scope.launch {
                dialog.show()
            }
        },
        Triple("ScrollableTabRow和LazyRow联动", false) {
            showView.intValue = 1
            scope.launch {
                dialog.show()
            }
        },
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("组合组件", true)
        ListCardView(items = items)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomDialog(showView: Int): SheetState {
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(true, confirmValueChange = { true })

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            containerColor = Black_24252C,
            dragHandle = null,
            contentWindowInsets = { WindowInsets(0,0,0,0) },
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            onDismissRequest = {
                scope.launch(Dispatchers.Default) { sheetState.hide() }
            }
        ) {
            AnimatedContent(showView, label = "") {
                when (it) {
                    0 -> {
                        ComponentPage()
                    }
                    1 -> {
                        TabWithLazyRow()
                    }
                }
            }
        }
    }

    return sheetState
}