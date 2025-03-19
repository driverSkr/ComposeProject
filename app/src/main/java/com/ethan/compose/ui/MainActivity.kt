package com.ethan.compose.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.base.BaseActivityVBind
import com.ethan.compose.databinding.LayoutComposeContainerBinding
import com.ethan.compose.theme.ComposeProjectTheme
import com.ethan.compose.theme.Transparent
import com.ethan.compose.ui.component.ComponentActivity
import com.ethan.compose.work.TestActivity
import com.skydoves.bundler.intentOf

class MainActivity : BaseActivityVBind<LayoutComposeContainerBinding>() {

    companion object {
        fun launch(context: Context) {
            context.intentOf<MainActivity> {
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
                            ExampleCode()
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun ExampleCode() {
    val context = LocalContext.current
    Column(modifier = Modifier.statusBarsPadding().padding(horizontal =  10.dp)) {
        Text(text = "基础组件", modifier = Modifier.align(Alignment.CenterHorizontally).clickable { ComponentActivity.launch(context) })

        Button(onClick = {
            context.startActivity(Intent(context, TestActivity::class.java))
        }) {
            Text(text = "Test")
        }
    }
}