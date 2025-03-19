package com.ethan.compose.custom.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.compose.R
import com.ethan.compose.extension.findBaseActivityVBind
import com.ethan.compose.theme.Purple40
import com.ethan.compose.theme.White

@Composable
fun StatusBarsView(title: String, canBack: Boolean = true) {
    val context = LocalContext.current

    Box(modifier = Modifier
        .background(color = Purple40)
        .statusBarsPadding()
        .fillMaxWidth()
        .height(50.dp)
        .wrapContentHeight()
        .padding(horizontal = 24.dp)
    ) {
        if (canBack) {
            Image(painter = painterResource(R.drawable.svg_back), contentDescription = null, modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { context.findBaseActivityVBind()?.finish() })
        }
        Text(text = title, color = White, fontSize = 16.sp, fontWeight = FontWeight.Bold, modifier = Modifier.align(Alignment.Center))
    }
}

@Preview
@Composable
fun PreStatusBarsView() {
    StatusBarsView("示例")
}