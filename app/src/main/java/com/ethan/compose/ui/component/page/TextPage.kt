package com.ethan.compose.ui.component.page

import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.ethan.compose.R
import com.ethan.compose.theme.Black
import com.ethan.compose.theme.Cyan
import com.ethan.compose.theme.DarkOrange
import com.ethan.compose.theme.Pink40
import com.ethan.compose.utils.LaunchUtils
import com.ethan.compose.utils.TextSpanUtils

@Composable
@Preview
/**
 * Text 对应View中的 TextView
 */
fun TextPage() {
    val brushColor = Brush.horizontalGradient(colorStops = arrayOf(0.5f to DarkOrange, 1f to Cyan))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp)
    ) {
        Text(
            text = "一个简单的文本",
            color = Color.Blue,
            fontSize = 24.sp
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "使用斜体",
            color = Black,
            fontWeight = FontWeight.W400,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic,
            softWrap = false,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "渐变色文本",
            style = TextStyle(
                brush = brushColor,
                fontWeight = FontWeight.W700,
                fontSize = 24.sp,
                fontStyle = FontStyle.Italic
            ),
            softWrap = false,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text("超限制展示省略号",
            fontSize = 16.sp,
            color = Pink40,
            fontWeight = FontWeight.W400,
            softWrap = false,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(100.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))

        ClickableText()
    }
}

@Composable
fun ClickableText() {
    AndroidView(
        factory = { context ->
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.textview, null, false)
            val content = "已阅读并同意用户协议和隐私政策"
            val span1 = "用户协议"
            val span2 = "隐私政策"
            val click1 = object : ClickableSpan() {
                override fun onClick(p0: View) {
                    LaunchUtils.launchWeb(context, "https://www.niuxuezhang.cn/app-html5/hitpaw-video-enhancer-app-terms-and-conditions.html", "用户协议")
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = false
                }
            }
            val click2 = object : ClickableSpan() {
                override fun onClick(p0: View) {
                    LaunchUtils.launchWeb(context, "https://www.niuxuezhang.cn/app-html5/hitpaw-video-enhancer-app-privacy-policy.html", "隐私政策")
                }

                override fun updateDrawState(ds: TextPaint) {
                    ds.isUnderlineText = false
                }
            }
            val spans = arrayOf(
                TextSpanUtils.Span(span1, R.color._0088FF, click1),
                TextSpanUtils.Span(span2, R.color._0088FF, click2)
            )
            TextSpanUtils.setSpanText(context, content, spans, view.findViewById(R.id.text))

            view
        },
        modifier = Modifier.wrapContentSize()
    )
}