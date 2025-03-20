package com.ethan.compose.ui.custom.page

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
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

    Box(modifier = Modifier.statusBarsPadding().fillMaxSize()) {
        Button(onClick = {
            scope.launch(Dispatchers.Default) {
                dialog.show()
            }
        }) {
            Text(text = "底部登陆弹窗")
        }
    }
}