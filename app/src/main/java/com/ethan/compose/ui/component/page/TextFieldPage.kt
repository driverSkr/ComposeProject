package com.ethan.compose.ui.component.page

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.compose.theme.White
import com.ethan.compose.theme.White10

@Composable
@Preview
fun TextFieldPage() {
    TextFieldExampleCode()
}

@Composable
fun TextFieldExampleCode() {
    Column(modifier = Modifier.padding(all = 10.dp)) {
        SimpleWidgetColumn()
    }
}

@Composable
fun SimpleWidgetColumn() {
    var userInput by remember { mutableStateOf("") }
    Column {
        TextFieldWidget(userInput, { newValue ->
            userInput = newValue
        })
    }
}

@Composable
fun TextFieldWidget(value: String, onValueChange: (String) -> Unit, modifier: Modifier = Modifier) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange
    )
}

@Composable
fun CustomBasicTextField(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = text,
        onValueChange = onValueChange,
        cursorBrush = SolidColor(White),
        textStyle = TextStyle.Default.copy(color = White, fontSize = 16.sp),
        modifier = modifier
            .background(color = White10, shape = RoundedCornerShape(6.dp))
            .padding(horizontal = 12.dp, vertical = 16.dp),
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth().height(IntrinsicSize.Min)) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        style = TextStyle(fontSize = 16.sp, color = Color.DarkGray),
                        modifier = Modifier.align(alignment = Alignment.CenterStart).padding(start = 16.dp)
                    )
                }
                innerTextField()
            }
        }
    )
}