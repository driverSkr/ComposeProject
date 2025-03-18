package com.ethan.base.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import com.ethan.base.R

/**
 * @author  PengHaiChen
 * @date    2023/12/25 20:23
 * @email   penghaichen@tenorshare.cn
 */
class FullWinDialog : DialogFragment() {

    var dialogView: View? = null
    var containerActivity: FragmentActivity? = null

    override fun getTheme(): Int {
        return R.style.full_win_dialog
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return dialogView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    fun showDialog() {
        if (containerActivity != null) {
            show(containerActivity!!.supportFragmentManager, "FullWinDialog")
        }
    }

    fun init(block: FullWinDialog.() -> Unit): FullWinDialog {
        block()
        return this
    }

}