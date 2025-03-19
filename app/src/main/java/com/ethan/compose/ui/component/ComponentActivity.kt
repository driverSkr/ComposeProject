package com.ethan.compose.ui.component

import android.content.Context
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ethan.compose.R
import com.ethan.compose.base.BaseActivityVBind
import com.ethan.compose.databinding.LayoutComposeContainerBinding
import com.ethan.compose.theme.ComposeProjectTheme
import com.ethan.compose.theme.Transparent
import com.ethan.compose.ui.ExampleCode
import com.ethan.compose.ui.MainActivity
import com.ethan.compose.ui.component.page.TextPage
import com.skydoves.bundler.intentOf

class ComponentActivity : BaseActivityVBind<LayoutComposeContainerBinding>() {

    companion object {
        fun launch(context: Context) {
            context.intentOf<ComponentActivity> {
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
                        Surface(modifier = Modifier.fillMaxSize().statusBarsPadding(), color = Transparent) {
                            ComponentPage()
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ComponentPage() {
    TextPage()
}

private fun clickLaunch(targetPage: String) {
    when(targetPage) {
        "Text" -> {}
        "Button" -> {}
        "TextField" -> {}
        "Image" -> {}
        "ProgressIndicator" -> {}
    }
}