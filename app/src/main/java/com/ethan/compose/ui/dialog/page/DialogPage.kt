package com.ethan.compose.ui.dialog.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.custom.widget.rememberConfirmDialog
import com.ethan.compose.custom.widget.rememberGiftBagDialog
import com.ethan.compose.custom.widget.rememberLoadingDialog
import com.ethan.compose.custom.widget.rememberLoadingWithTitleDialog
import com.ethan.compose.extension.findBaseActivityVBind
import com.ethan.compose.utils.DialogHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
@Preview
fun DialogPage() {

    val scope = rememberCoroutineScope()
    val content = LocalContext.current
    val activity = content.findBaseActivityVBind()

    val confirmDialog = rememberConfirmDialog(
        title = "标题",
        content = "你好，弹窗！",
        mainTv = "确认",
        secondaryTv = "取消",
        mainBtn = { /*TODO*/ },
        secondaryBtn = {})

    val confirmDialog2 = rememberConfirmDialog(
        content = "你好，弹窗！",
        mainTv = "确认",
        secondaryTv = "取消",
        mainBtn = { /*TODO*/ },
        secondaryBtn = {})

    val dialogWithTitle = rememberLoadingWithTitleDialog("请稍等")

    val dialog = rememberLoadingDialog()

    val giftDialog = rememberGiftBagDialog(30)

    Column(modifier = Modifier.statusBarsPadding().fillMaxSize()) {
        Button(onClick = { confirmDialog.value = true }) {
            Text(text = "确认弹窗")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { activity?.let { DialogHelper.showUpdateDialog(it) } }) {
            Text(text = "确认弹窗（XML版）")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { confirmDialog2.value = true }) {
            Text(text = "确认弹窗2")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            scope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    dialog.value = true
                }
                delay(5000)
                withContext(Dispatchers.Main) {
                    dialog.value = false
                }
            }

        }) {
            Text(text = "加载弹窗")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = {
            scope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    dialogWithTitle.value = true
                }
                delay(5000)
                withContext(Dispatchers.Main) {
                    dialogWithTitle.value = false
                }
            }

        }) {
            Text(text = "加载弹窗带标题")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { giftDialog.value = true }) {
            Text(text = "礼包弹窗")
        }
    }
}