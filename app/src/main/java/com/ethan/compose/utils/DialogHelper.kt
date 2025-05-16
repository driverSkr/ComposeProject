package com.ethan.compose.utils

import android.view.LayoutInflater
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import com.ethan.base.dialog.BaseDialog
import com.ethan.compose.databinding.DialogOperatePromptBinding
import com.ethan.compose.extension.antiShakeClick
import com.ethan.compose.theme.ComposeProjectTheme
import com.ethan.compose.theme.Green
import com.ethan.compose.ui.dialog.ComposeNativeDialog

object DialogHelper {

    //提示弹窗
    fun showConfirmDialog(
        activity: FragmentActivity,
        tip: String,                           //提示文本
        content: String,                       //内容
        mainTv: String,                        //主按钮内容
        secondaryTv: String? = null,           //次级按钮内容
        mainBtn: () -> Unit,                   //主按钮触发方法
        secondaryBtn: () -> Unit               //次级按钮触发方法
    ) {
        val binding = DialogOperatePromptBinding.inflate(LayoutInflater.from(activity))
        binding.title.text = tip
        binding.promptDescribe.text = content
        binding.btnOneTv.text = secondaryTv
        binding.btnTwoTv.text = mainTv

        val dialog = BaseDialog
            .Builder(activity).setCancelableOutside(false).setView(binding.root).create()
        val show = dialog.show()
        binding.close.antiShakeClick {
            show?.dismiss()
        }
        binding.btnOne.antiShakeClick {
            secondaryBtn.invoke()
            show?.dismiss()
        }
        binding.btnTwo.antiShakeClick {
            mainBtn.invoke()
            show?.dismiss()
        }
    }

    fun showUpdateDialog(activity: FragmentActivity) {
        val binding = DialogOperatePromptBinding.inflate(LayoutInflater.from(activity))

        val dialog = BaseDialog
            .Builder(activity).setCancelableOutside(false).setView(binding.root).create()
        val show = dialog.show()
        binding.close.antiShakeClick {
            show?.dismiss()
        }
        binding.btnOne.antiShakeClick {
            show?.dismiss()
        }
        binding.btnTwo.antiShakeClick {
            show?.dismiss()
        }
    }

    fun showTestDialog(activity: FragmentActivity) {
        val (binding, dialog) = ComposeNativeDialog.composeBSDialog(activity)
        val show = dialog.show()
        binding.composeView.apply {
            setContent {
                ComposeProjectTheme {
                    Box(modifier = Modifier.height(500.dp).background(color = Green))
                }
            }
        }
    }
}