package com.ethan.compose.ui.custom.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ethan.compose.R
import com.ethan.compose.theme.Black
import com.ethan.compose.theme.Black10
import com.ethan.compose.theme.NO_PADDING_TEXT_STYLE
import com.ethan.compose.theme.White
import com.ethan.compose.utils.antiShakeClick

@Composable
fun ListCardView(items: List<Triple<String, Boolean, () -> Any>>) {
    Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = White, contentColor = Black, disabledContainerColor = White, disabledContentColor = Black),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.0.dp),
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 12.dp),
    ) {
        LazyColumn(modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()) {
            items(items) {
                ItemView(it.first, isNotEnd = it != items.last(), isNeedEndIcon = it.second, onClick = it.third)
            }
        }
    }
}

@Composable
@Preview
fun ItemView(name: String = "示例", isNotEnd: Boolean = true, isNeedEndIcon: Boolean = true, onClick: () -> Any = {}, modifier: Modifier = Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 12.dp)
        .antiShakeClick {
            onClick()
        }
    ) {
        Text(
            name,
            color = Black,
            fontSize = 16.sp,
            style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400),
            modifier = Modifier.align(Alignment.CenterStart)
        )

        if (isNeedEndIcon) {
            Image(
                painter = painterResource(id = R.drawable.svg_icon_next_black40),
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterEnd)
            )
        }

        if (isNotEnd) {
            HorizontalDivider(
                thickness = 1.dp,
                color = Black10,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .clip(RoundedCornerShape(999.dp))
            )
        }
    }
}