package com.ethan.compose.ui.composite.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ethan.compose.R
import com.ethan.compose.theme.Purple1F1B26
import com.ethan.compose.theme.White
import com.ethan.compose.ui.composite.model.RemoveItemData
import com.ethan.compose.utils.antiShakeClick
import com.ethan.compose.utils.setAlpha

@Composable
fun VideoSelectView(isSelected: Boolean, modifier: Modifier = Modifier, clickable: () -> Unit) {

    Box(modifier =
    if (isSelected) {
        modifier
            .antiShakeClick {
                clickable.invoke()
            }
            .size(64.dp)
            .border(
                width = 2.dp,
                color = White,
                shape = RoundedCornerShape(12.dp)
            )
            .padding(4.dp)
            .background(color = Purple1F1B26, shape = RoundedCornerShape(8.dp))
    } else {
        modifier
            .antiShakeClick {
                clickable.invoke()
            }
            .size(64.dp)
            .background(color = Purple1F1B26, shape = RoundedCornerShape(16.dp))
    }
    ) {
        Image(painter = painterResource(R.drawable.svg_icon_add), contentDescription = "add video", modifier = Modifier.align(Alignment.Center))
    }

}

@Composable
fun RemoveItem(
    isSelected: Boolean,
    isLoading: MutableState<Boolean>,
    detail: RemoveItemData,
    clickable: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .size(64.dp)
        .clickable { clickable.invoke() }
    ) {
        Box(modifier =
        if (isSelected) {
            Modifier
                .border(
                    width = 2.dp,
                    color = White,
                    shape = if (detail.is_free == 2) RoundedCornerShape(topEnd = 16.dp, topStart = 12.dp, bottomEnd = 12.dp, bottomStart = 12.dp) else RoundedCornerShape(12.dp)
                )
                .padding(4.dp)
                .clip(RoundedCornerShape(8.dp))
        } else {
            Modifier.clip(RoundedCornerShape(16.dp))
        }
        ) {
            AsyncImage(
                model = detail.preview_url,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentScale = ContentScale.Crop,
                placeholder = painterResource(id = R.mipmap.load_error_img),
                error = painterResource(id = R.mipmap.load_error_img)
            )
        }

        if (detail.is_free == 2) {
            Image(
                painter = painterResource(R.drawable.svg_icon_pro),
                contentDescription = "pro",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .clip(RoundedCornerShape(topEnd = 16.dp))
            )
        }
        AnimatedVisibility(isLoading.value) {
            Box(Modifier
                .fillMaxSize()
                .background(Color.Black.setAlpha(0.3f))) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(20.dp),
                    color = Color.White,
                    trackColor = Color.White.setAlpha(0.1F),
                    strokeCap = StrokeCap.Round
                )
            }

        }

    }
}

@Composable
@Preview
fun VideoSelectViewPreview() {
    VideoSelectView(true) { }
}

@Composable
@Preview
fun VideoSelectViewPreview2() {
    VideoSelectView(false) { }
}

