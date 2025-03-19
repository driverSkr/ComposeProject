package com.ethan.compose.ui.component.page

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.ethan.compose.R
import com.ethan.compose.extension.findBaseActivityVBind

@Composable
@Preview
fun ComponentPage() {
    val context = LocalContext.current
    val pagerState = rememberPagerState(initialPage = 0) { 5 }

    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            Image(painter = painterResource(R.drawable.svg_back_black), contentDescription = null, modifier = Modifier
                .align(Alignment.CenterStart)
                .clickable { context.findBaseActivityVBind()?.finish() })
            Text(text = "基础组件", modifier = Modifier.align(Alignment.Center))
        }

        HorizontalPager(pagerState, beyondViewportPageCount = 2) {
            when (it) {
                0 -> TextPage()
                1 -> ButtonPage()
                2 -> ImagePage()
                3 -> ProgressIndicatorPage()
                4 -> TextFieldPage()
            }
        }
    }
}