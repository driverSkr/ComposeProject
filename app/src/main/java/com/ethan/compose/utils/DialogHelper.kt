package com.ethan.compose.utils

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.ethan.base.dialog.BaseDialog
import com.ethan.compose.databinding.DialogOperatePromptBinding
import com.ethan.compose.extension.antiShakeClick

object DialogHelper {

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
}