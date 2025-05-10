package com.ethan.compose.ui.component.page

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.background
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.ui.custom.view.ListCardView
import com.ethan.compose.ui.custom.view.StatusBarsView
import com.ethan.compose.theme.White
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * 基础组件
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun ComponentPage() {
    val scope = rememberCoroutineScope()
    val showView = remember { mutableIntStateOf(0) }
    val dialog = bottomDialog(showView.intValue)

    val items = listOf(
        Triple("Text", true) {
            showView.intValue = 0
            scope.launch {
                dialog.show()
            }
        },
        Triple("Button", true) {
            showView.intValue = 1
            scope.launch {
                dialog.show()
            }
        },
        Triple("Image", true) {
            showView.intValue = 2
            scope.launch {
                dialog.show()
            }
        },
        Triple("ProgressIndicator", true) {
            showView.intValue = 3
            scope.launch {
                dialog.show()
            }
        },
        Triple("TextField", true) {
            showView.intValue = 4
            scope.launch {
                dialog.show()
            }
        },
    )

    Column(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        StatusBarsView("基础组件")
        ListCardView(items)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun bottomDialog(showView: Int): SheetState {
    val scope = rememberCoroutineScope()
    //skipPartiallyExpanded = false 部分展开
    val sheetState = rememberModalBottomSheetState(true, confirmValueChange = { true })

    if (sheetState.isVisible) {
        ModalBottomSheet(
            sheetState = sheetState,
            containerColor = White,
            dragHandle = null,
            contentWindowInsets = { WindowInsets(0,0,0,0) },
            shape = RoundedCornerShape(topStart = 12.dp, topEnd = 12.dp),
            onDismissRequest = {
                scope.launch(Dispatchers.Default) { sheetState.hide() }
            }
        ) {
            AnimatedContent(showView, label = "") {
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

    return sheetState
}