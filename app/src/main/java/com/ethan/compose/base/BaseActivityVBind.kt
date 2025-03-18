package com.ethan.compose.base

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.activity.SystemBarStyle
import androidx.activity.enableEdgeToEdge
import androidx.viewbinding.ViewBinding
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LanguageUtils
import com.ethan.base.component.BaseActivityVB
import com.ethan.permission.PermissionUtils
import java.util.Locale

open class BaseActivityVBind<T: ViewBinding>: BaseActivityVB<T>() {
    var permissionUtils = PermissionUtils()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        permissionUtils.init(this)
        fitStateBarHeight()
        initObserver()
    }

    override fun onResume() {
        super.onResume()
    }

    fun edge2EdgeWithCompose() {
        enableEdgeToEdge(SystemBarStyle.dark(Color.TRANSPARENT), SystemBarStyle.dark(Color.TRANSPARENT)) // 这个东西在设置透明的时候会自动顶到边缘
    }

    private fun fitStateBarHeight() {
        setFitStatusBarHeightView()?.let { BarUtils.addMarginTopEqualStatusBarHeight(it) }
    }

    private fun initObserver() {

    }

    open fun setFitStatusBarHeightView(): View? {
        return null
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
    }

    protected fun setLocale(languageCode: String) {
        val resources = resources
        val configuration = resources.configuration
        val displayMetrics = resources.displayMetrics
        if (languageCode == "tw") {
            configuration.setLocale(Locale("zh", "TW", "TW"))
        } else {
            configuration.setLocale(Locale(languageCode))
        }
        createConfigurationContext(configuration)
        resources.updateConfiguration(configuration, displayMetrics)
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(newBase)
    }

    companion object {
        var currLanguage: Locale = LanguageUtils.getSystemLanguage()
    }

    fun <T> getIntent(name: String, classF: Class<T>): T? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(name, classF)
        } else {
            intent.getParcelableExtra(name)
        }
    }

}