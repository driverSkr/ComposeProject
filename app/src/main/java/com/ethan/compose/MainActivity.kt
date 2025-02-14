package com.ethan.compose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.ui.theme.ComposeProjectTheme
import com.ethan.compose.view.work.removeTest.TestActivity
import com.ethan.compose.widget.ButtonActivity
import com.ethan.compose.widget.ImageActivity
import com.ethan.compose.widget.ProgressIndicatorActivity
import com.ethan.compose.widget.TextActivity
import com.ethan.compose.widget.TextFieldActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ExampleCode()
                }
            }
        }
    }
}

@Composable
fun ExampleCode() {
    val context = LocalContext.current
    Column(modifier = Modifier.padding(all = 10.dp)) {
        Text(text = "基础组件", modifier = Modifier.align(Alignment.CenterHorizontally))

        Button(onClick = {
            context.startActivity(Intent(context, TextActivity::class.java))
        }) {
            Text(text = "Text")
        }

        Button(onClick = {
            context.startActivity(Intent(context, ButtonActivity::class.java))
        }) {
            Text(text = "Button")
        }

        Button(onClick = {
            context.startActivity(Intent(context, TextFieldActivity::class.java))
        }) {
            Text(text = "TextField")
        }

        Button(onClick = {
            context.startActivity(Intent(context, ImageActivity::class.java))
        }) {
            Text(text = "Image")
        }

        Button(onClick = {
            context.startActivity(Intent(context, ProgressIndicatorActivity::class.java))
        }) {
            Text(text = "ProgressIndicator")
        }

        Button(onClick = {
            context.startActivity(Intent(context, TestActivity::class.java))
        }) {
            Text(text = "Test")
        }
    }
}

@Preview
@Composable
fun PreviewContent() {
    ComposeProjectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ExampleCode()
        }
    }
}