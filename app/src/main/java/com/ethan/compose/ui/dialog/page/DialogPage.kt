package com.ethan.compose.ui.dialog.page

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.custom.view.StatusBarsView
import com.ethan.compose.custom.widget.rememberConfirmDialog
import com.ethan.compose.custom.widget.rememberGiftBagDialog
import com.ethan.compose.custom.widget.rememberLoadingDialog
import com.ethan.compose.custom.widget.rememberLoadingWithTitleDialog
import com.ethan.compose.extension.findBaseActivityVBind
import com.ethan.compose.theme.Black
import com.ethan.compose.theme.White
import com.ethan.compose.ui.main.page.ItemView
import com.ethan.compose.utils.DialogHelper
import com.ethan.compose.utils.antiShakeClick
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

    Column(modifier = Modifier.fillMaxSize()) {
        StatusBarsView("弹窗组件")

        Card(
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(containerColor = White, contentColor = Black, disabledContainerColor = White, disabledContentColor = Black),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.0.dp),
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(horizontal = 12.dp),
        ) {
            Column(modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ) {
                ItemView("确认弹窗", isNeedEndIcon = false, modifier = Modifier.antiShakeClick { confirmDialog.value = true })
                ItemView("确认弹窗（XML版）", isNeedEndIcon = false, modifier = Modifier.antiShakeClick { activity?.let { DialogHelper.showUpdateDialog(it) } })
                ItemView("确认弹窗2", isNeedEndIcon = false, modifier = Modifier.antiShakeClick { confirmDialog2.value = true })
                ItemView("加载弹窗", isNeedEndIcon = false, modifier = Modifier.antiShakeClick {
                    scope.launch(Dispatchers.Default) {
                        withContext(Dispatchers.Main) {
                            dialog.value = true
                        }
                        delay(5000)
                        withContext(Dispatchers.Main) {
                            dialog.value = false
                        }
                    }
                })
                ItemView("加载弹窗带标题", isNeedEndIcon = false, modifier = Modifier.antiShakeClick {
                    scope.launch(Dispatchers.Default) {
                        withContext(Dispatchers.Main) {
                            dialogWithTitle.value = true
                        }
                        delay(5000)
                        withContext(Dispatchers.Main) {
                            dialogWithTitle.value = false
                        }
                    }
                })
                ItemView("礼包弹窗", isNotEnd = false, isNeedEndIcon = false, modifier = Modifier.antiShakeClick { giftDialog.value = true })
            }
        }
    }
}