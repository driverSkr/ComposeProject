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
import com.ethan.compose.ui.technique.page.TechniquePage
import com.skydoves.bundler.intentOf

class TechniqueActivity : BaseActivityVBind<LayoutComposeContainerBinding>() {

    companion object {
        fun launch(context: Context) {
            context.intentOf<TechniqueActivity> {
                startActivity(context)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.composeView.apply {
            setContent {
                CompositionLocalProvider {
                    ComposeProjectTheme {
                        Surface(modifier = Modifier.fillMaxSize(), color = Transparent) {
                            TechniquePage()
                        }
                    }
                }
            }
        }
    }
}