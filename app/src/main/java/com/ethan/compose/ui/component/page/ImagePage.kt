package com.ethan.compose.ui.component.page

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import coil.decode.GifDecoder
import coil.request.ImageRequest
import coil.request.repeatCount
import com.ethan.compose.R

@Composable
@Preview
/**
 * Image 对应View中的 ImageView
 */
fun ImagePage() {

    val context = LocalContext.current

    val bitmap1 = BitmapFactory.decodeResource(context.resources, R.mipmap.banner_01)
    val imageRequest = remember {
        ImageRequest.Builder(context)
            .repeatCount(10)
            .data("android.resource://${context.packageName}/${R.raw.app_logo_gif}")
            .decoderFactory(GifDecoder.Factory())
            .build()
    }

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight(0.7f)
        .padding(all = 10.dp)
        .navigationBarsPadding()
        .verticalScroll(rememberScrollState())
    ) {

        Text(text = "基本使用")
        Image(painter = painterResource(id = R.mipmap.banner_01), contentDescription = "A woman")

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Compose中专有的bitmap形式图片")
        val bitmap: ImageBitmap = ImageBitmap.imageResource(id = R.mipmap.banner_01)
        Image(bitmap = bitmap, contentDescription = "A woman")

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "传统的bitmap对象图片")
        Image(bitmap = bitmap1.asImageBitmap(), contentDescription = "A woman")

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "使用网络图片")
        AsyncImage(model = "https://img-blog.csdnimg.cn/20200401094829557.jpg", contentDescription = "First line of code")

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "播放Gif动图")
        SubcomposeAsyncImage(
            model = imageRequest,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            loading = {
                Image(painter = painterResource(R.mipmap.banner_01), contentDescription = null)
            },
            success = { success ->
                SubcomposeAsyncImageContent(painter = success.painter, contentDescription = null)
            }
        )
    }
}