package com.ethan.compose.widget

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.ethan.compose.R
import com.ethan.compose.ui.theme.ComposeProjectTheme


/**
 * Image 对应View中的 ImageView
 */
class ImageActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ImageExampleCode()
                }
            }
        }
    }
}

@Composable
fun ImageExampleCode() {
    Column(modifier = Modifier.padding(all = 10.dp)) {
        Text(text = "基本使用")
        Image(painter = painterResource(id = R.mipmap.banner_01), contentDescription = "A woman")

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "Compose中专有的bitmap形式图片")
        val bitmap: ImageBitmap = ImageBitmap.imageResource(id = R.mipmap.banner_01)
        Image(bitmap = bitmap, contentDescription = "A woman")

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "传统的bitmap对象图片")
        val context = LocalContext.current
        val bitmap1 = BitmapFactory.decodeResource(context.resources, R.mipmap.banner_01)
        Image(bitmap = bitmap1.asImageBitmap(), contentDescription = "A woman")

        Spacer(modifier = Modifier.height(10.dp))
        Text(text = "使用网络图片")
        AsyncImage(model = "https://img-blog.csdnimg.cn/20200401094829557.jpg", contentDescription = "First line of code")
    }
}

@Preview
@Composable
fun ImagePreviewContent() {
    ComposeProjectTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ImageExampleCode()
        }
    }
}