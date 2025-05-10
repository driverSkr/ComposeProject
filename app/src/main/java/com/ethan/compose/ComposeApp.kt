package com.ethan.compose

import com.ethan.base.component.BaseApp

class ComposeApp: BaseApp() {
    companion object {
        var INSTANCE: ComposeApp? = null
            private set
    }
    override fun initLibs() {
        INSTANCE = this
    }
}