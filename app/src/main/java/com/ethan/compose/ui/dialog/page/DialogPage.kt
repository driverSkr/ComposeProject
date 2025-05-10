package com.ethan.compose.ui.dialog.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.ui.custom.view.ListCardView
import com.ethan.compose.ui.custom.view.StatusBarsView
import com.ethan.compose.ui.dialog.view.rememberConfirmDialog
import com.ethan.compose.ui.dialog.view.rememberGiftBagDialog
import com.ethan.compose.ui.dialog.view.rememberLoadingDialog
import com.ethan.compose.ui.dialog.view.rememberLoadingWithTitleDialog
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
    val context = LocalContext.current
    val activity = context.findBaseActivityVBind()

    val confirmDialog = rememberConfirmDialog(
        title = "标题",
        content = "你好，弹窗！",
        mainTv = "确认",
        secondaryTv = "取消",
        mainBtn = { /*TODO*/ },
        secondaryBtn = {})

    val confirmDialog2 = rememberConfirmDialog(
        title = "标题",
        content = "你好，弹窗！",
        mainTv = "确认",
        secondaryTv = "取消",
        mainBtn = { /*TODO*/ },
        secondaryBtn = {})

    val dialogWithTitle = rememberLoadingWithTitleDialog("请稍等")

    val dialog = rememberLoadingDialog()

    val giftDialog = rememberGiftBagDialog(30)

    val items = listOf(
        Triple("确认弹窗", false) { confirmDialog.value = true },
        Triple("确认弹窗（XML版）", false) { activity?.let { DialogHelper.showUpdateDialog(it) } ?: {} },
        Triple("确认弹窗2", false) { confirmDialog2.value = true },
        Triple("加载弹窗", false) {
            scope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    dialog.value = true
                }
                delay(5000)
                withContext(Dispatchers.Main) {
                    dialog.value = false
                }
            }
        },
        Triple("加载弹窗带标题", false) {
            scope.launch(Dispatchers.Default) {
                withContext(Dispatchers.Main) {
                    dialogWithTitle.value = true
                }
                delay(5000)
                withContext(Dispatchers.Main) {
                    dialogWithTitle.value = false
                }
            }
        },
        Triple("礼包弹窗", false) { giftDialog.value = true },
    )

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("弹窗组件")
        ListCardView(items)
    }
}