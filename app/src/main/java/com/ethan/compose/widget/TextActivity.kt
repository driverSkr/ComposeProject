package com.ethan.compose.widget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.compose.ui.theme.ComposeProjectTheme
import com.ethan.compose.view.work.rememberConfirmDialog

/**
* Text 对应View中的 TextView
*/
class TextActivity : ComponentActivity() {
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
    val dialog = rememberConfirmDialog(
        title = "标题",
        content = "你好，弹窗！",
        mainTv = "确认",
        secondaryTv = "取消",
        mainBtn = { /*TODO*/ },
        secondaryBtn = {})

    Column(modifier = Modifier.padding(all = 10.dp)) {
        Text(
            text = "This is Text",
            color = Color.Blue,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(onClick = { dialog.value = true }) {}
    }
}

// @Preview(
//     name = "Dark Mode",
//     uiMode = Configuration.UI_MODE_NIGHT_YES, //夜间模式
//     showBackground = true
// )
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