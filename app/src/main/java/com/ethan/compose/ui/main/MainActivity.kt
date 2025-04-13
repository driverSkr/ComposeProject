package com.ethan.compose.ui.main

import android.content.Context
import android.os.Bundle
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import com.ethan.compose.base.BaseActivityVBind
import com.ethan.compose.databinding.LayoutComposeContainerBinding
import com.ethan.compose.theme.ComposeProjectTheme
import com.ethan.compose.theme.Transparent
import com.ethan.compose.ui.main.page.MainPage
import com.skydoves.bundler.intentOf

//todo 1.状态同步的几种实现方式（接口回调、livedata、flow、EventBus、BroadcastReceiver、SharedPreferences/DataStore、mutableStateOf）
//todo 2.音频录制、播放的多种方式
//todo 3.数据库存储的使用（room）
//todo 4.视频播放的多种方式（MediaPlayer、ExoPlayer）
//todo 5.音视频裁剪（ffmpeg）
//todo 6.项目结构调整（可以按“设置”app设计）
class MainActivity : BaseActivityVBind<LayoutComposeContainerBinding>() {

    companion object {
        fun launch(context: Context) {
            context.intentOf<MainActivity> {
                startActivity(context)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.composeView.apply {
            setContent {
                CompositionLocalProvider {
                    ComposeProjectTheme {
                        Surface(modifier = Modifier.fillMaxSize(), color = Transparent) {
                            MainPage()
                        }
                    }
                }
            }
        }
    }
}