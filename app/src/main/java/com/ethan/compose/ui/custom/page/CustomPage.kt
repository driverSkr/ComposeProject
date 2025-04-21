package com.ethan.compose.ui.custom.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.custom.view.ListCardView
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.custom.widget.rememberLoginDialog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun CustomPage() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val dialog = rememberLoginDialog(context)
    val items = listOf(
        Triple("底部登陆弹窗", false) {
            scope.launch(Dispatchers.Default) {
                dialog.show()
            }
        }
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("自定义组件")
        ListCardView(items)
    }
}