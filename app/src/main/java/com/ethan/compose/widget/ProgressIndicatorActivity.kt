package com.ethan.compose.widget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ethan.compose.ui.theme.ComposeProjectTheme

/**
 * Button 对应View中的 Button
 */
class ProgressIndicatorActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ProgressIndicatorExampleCode()
                }
            }
        }
    }
}

@Composable
fun ProgressIndicatorExampleCode() {
    Column(modifier = Modifier.padding(all = 10.dp)) {
        Text(text = "圆形进度条")
        CircularProgressIndicator(
            color = Color.Green,
            strokeWidth = 6.dp
        )

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "长条形进度条")
        LinearProgressIndicator(
            color = Color.Blue,
            trackColor = Color.Gray
        )
    }
}

@Preview
@Composable
fun ProgressIndicatorPreviewContent() {
    ComposeProjectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ProgressIndicatorExampleCode()
        }
    }
}