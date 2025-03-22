package com.ethan.compose.ui.component.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.compose.theme.NO_PADDING_TEXT_STYLE
import com.ethan.compose.theme.Transparent
import com.ethan.compose.theme.White
import com.ethan.compose.theme.White10
import com.ethan.compose.theme.White40

@Composable
@Preview
fun TextFieldPage() {
    TextFieldExampleCode()

    Spacer(modifier = Modifier.height(10.dp))

    TextInput("", {}, "")
}


@Composable
fun TextInput(
    text: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier,
) {

    TextField(
        value = text,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = { Text(hint, fontSize = 14.sp, color = White40, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400)) },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = White10, // 聚焦时背景颜色
            unfocusedContainerColor = White10, // 未聚焦时背景颜色
            disabledContainerColor = White10, // 禁用时背景颜色
            cursorColor = White, // 光标颜色
            focusedTextColor = White, // 聚焦时文本颜色
            unfocusedTextColor = White, // 未聚焦时文本颜色
            focusedIndicatorColor = Transparent, // 聚焦时指示器颜色
            unfocusedIndicatorColor = Transparent, // 未聚焦时指示器颜色
            disabledIndicatorColor = Transparent, // 禁用时指示器颜色
        ),
        shape = MaterialTheme.shapes.small.copy(
            topStart = CornerSize(6.dp),
            topEnd = CornerSize(6.dp),
            bottomStart = CornerSize(6.dp),
            bottomEnd = CornerSize(6.dp)),
        singleLine = true,
        modifier = modifier.fillMaxWidth())
}

@Composable
fun TextFieldExampleCode() {
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(all = 10.dp)) {
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
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)) {
                if (text.isEmpty()) {
                    Text(
                        text = hint,
                        style = TextStyle(fontSize = 16.sp, color = Color.DarkGray),
                        modifier = Modifier
                            .align(alignment = Alignment.CenterStart)
                            .padding(start = 16.dp)
                    )
                }
                innerTextField()
            }
        }
    )
}