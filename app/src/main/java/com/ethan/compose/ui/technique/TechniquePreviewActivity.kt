package com.ethan.compose.ui.technique

import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.ethan.compose.base.BaseActivityVBind
import com.ethan.compose.databinding.LayoutComposeContainerBinding
import com.ethan.compose.theme.ComposeProjectTheme
import com.ethan.compose.theme.Transparent
import com.ethan.compose.ui.technique.model.PageType
import com.ethan.compose.ui.technique.page.LanguagePage
import com.ethan.compose.ui.technique.page.TechniquePage
import com.skydoves.bundler.bundle
import com.skydoves.bundler.intentOf

class TechniquePreviewActivity : BaseActivityVBind<LayoutComposeContainerBinding>() {

    companion object {
        fun launch(context: Context, pageType: PageType) {
            context.intentOf<TechniquePreviewActivity> {
                +("pageType" to pageType)
                startActivity(context)
            }
        }
    }

    private val pageType by bundle<PageType>("pageType")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.composeView.apply {
            setContent {
                CompositionLocalProvider {
                    ComposeProjectTheme {
                        Surface(modifier = Modifier.fillMaxSize(), color = Transparent) {
                            when(pageType) {
                                PageType.LanguagePage -> LanguagePage()
                                else -> TechniquePage()
                            }
                        }
                    }
                }
            }
        }
    }
}