package com.ethan.compose.ui.media.view

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.blankj.utilcode.util.PermissionUtils
import com.ethan.compose.R
import com.ethan.compose.custom.widget.cloneProgressDialog
import com.ethan.compose.custom.widget.rememberConfirmDialog
import com.ethan.compose.theme.Grey20
import com.ethan.compose.theme.NO_PADDING_TEXT_STYLE
import com.ethan.compose.theme.White
import com.ethan.compose.theme.White10
import com.ethan.compose.theme.White60
import com.ethan.compose.utils.AudioRecordHelper
import com.ethan.compose.utils.MyPermissionUtils
import com.ethan.compose.utils.ToastType
import com.ethan.compose.utils.antiShakeClick
import com.ethan.compose.utils.formatMSCTime
import com.ethan.compose.utils.getAudioName
import com.ethan.compose.utils.invisible
import com.ethan.compose.utils.showToast
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File

@Composable
@Preview
/** 音频录制、预览 */
fun AudioRecordView(modifier: Modifier = Modifier, isNeedCutting: Boolean = false) {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val toCuttingPage = remember { mutableStateOf(false) }
    val audioHelper = remember { AudioRecordHelper() }
    val outputFile = remember { mutableStateOf(File("")) }
    val audioPlayBtn = if (audioHelper.playState == AudioRecordHelper.PlayState.PLAYING) R.drawable.svg_icon_record_play else R.drawable.svg_icon_record_pause

    val dialog = cloneProgressDialog()
    val permissionDialog = rememberConfirmDialog(
        "请求权限",
        "请求录音权限",
        "去应用设置",
        "取消",
        { PermissionUtils.launchAppDetailsSettings() }
    )

    // 确保在Composable退出时清理资源
    DisposableEffect(Unit) {
        onDispose {
            audioHelper.release()
        }
    }

    AnimatedContent(toCuttingPage.value, label = "") { toCutting ->
        if (toCutting && isNeedCutting) {
            AudioCuttingView(outputFile.value.path)
        } else {
            AnimatedContent(audioHelper.recordState, label = "") {
                val recordBtn = when (it) {
                    AudioRecordHelper.RecordState.IDLE -> R.drawable.svg_icon_voice_recording
                    AudioRecordHelper.RecordState.RECORDING -> R.drawable.svg_icon_voice_record_pause
                    AudioRecordHelper.RecordState.PAUSED -> R.drawable.svg_icon_voice_continue
                }
                Column(modifier = modifier.fillMaxSize().navigationBarsPadding(), horizontalAlignment = Alignment.CenterHorizontally) {

                    Row(modifier = Modifier
                        .invisible(it == AudioRecordHelper.RecordState.RECORDING)
                        .wrapContentSize()
                        .background(color = White10, shape = RoundedCornerShape(24.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        BreathingLight()
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("recording",color = White, fontSize = 12.sp, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400))
                    }

                    Box(modifier = Modifier.weight(1f).fillMaxWidth().background(color = White10)) {

                        Image(painter = painterResource(audioPlayBtn), contentDescription = null, modifier = Modifier
                            .align(Alignment.Center)
                            .invisible(it == AudioRecordHelper.RecordState.PAUSED)
                            .antiShakeClick {
                                if (audioHelper.playState == AudioRecordHelper.PlayState.PLAYING) {
                                    audioHelper.pausePlaying()
                                } else {
                                    audioHelper.startPlaying()
                                }
                            }
                        )
                    }

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        if (it == AudioRecordHelper.RecordState.PAUSED && audioHelper.playState == AudioRecordHelper.PlayState.PLAYING) {
                            Text(audioHelper.currentPlayPositionMs.formatMSCTime(),color = White60, fontSize = 14.sp, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400))
                            Box(modifier = Modifier.padding(horizontal = 7.dp).width(1.dp).height(12.dp).background(color = Grey20, shape = RoundedCornerShape(2.dp)))
                        }
                        Text(audioHelper.currentDurationMs.formatMSCTime(),color = White60, fontSize = 14.sp, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400))
                        if (it != AudioRecordHelper.RecordState.IDLE && audioHelper.playState != AudioRecordHelper.PlayState.PLAYING) {
                            Box(modifier = Modifier.padding(horizontal = 7.dp).width(1.dp).height(12.dp).background(color = Grey20, shape = RoundedCornerShape(2.dp)))
                            Text("03:00:00",color = White60, fontSize = 14.sp, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400))
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(painter = painterResource(R.drawable.svg_icon_voice_delete), contentDescription = null, modifier = Modifier
                            .invisible(it == AudioRecordHelper.RecordState.PAUSED && audioHelper.playState != AudioRecordHelper.PlayState.PLAYING)
                            .antiShakeClick {
                                audioHelper.deleteFile()
                                "删除成功".showToast(context, ToastType.SUCCESS)
                            }
                        )
                        Image(painter = painterResource(recordBtn), contentDescription = null, modifier = Modifier
                            .padding(horizontal = 48.dp)
                            .antiShakeClick {
                                scope.launch(Dispatchers.Default) {
                                    val granted = MyPermissionUtils.checkRecordPermission(false, jump2Setting = false)
                                    if (granted) {
                                        when(it) {
                                            AudioRecordHelper.RecordState.IDLE -> {
                                                context.externalCacheDir?.let { dir -> audioHelper.startRecording(context, dir) }
                                            }
                                            AudioRecordHelper.RecordState.RECORDING -> {
                                                context.externalCacheDir?.let { dir -> audioHelper.pauseRecording(dir) }
                                            }
                                            AudioRecordHelper.RecordState.PAUSED -> {
                                                context.externalCacheDir?.let { dir -> audioHelper.startRecording(context, dir) }
                                            }
                                        }
                                    } else {
                                        permissionDialog.value = true
                                    }
                                }
                            })
                        Image(painter = painterResource(R.drawable.svg_icon_voice_save), contentDescription = null, modifier = Modifier
                            .invisible(it == AudioRecordHelper.RecordState.PAUSED && audioHelper.playState != AudioRecordHelper.PlayState.PLAYING)
                            .antiShakeClick {
                                scope.launch(Dispatchers.Default) {
                                    val audioDir = File(context.externalCacheDir, "cache_audio").apply { mkdirs() }
                                    outputFile.value = File(audioDir, getAudioName()).apply { createNewFile() }
                                    val result = audioHelper.stopRecording(outputFile.value){}
                                    if (result) {
                                        if (isNeedCutting) {
                                            toCuttingPage.value = true
                                        } else {
                                            withContext(Dispatchers.Main) {
                                                dialog.value = true
                                            }
                                        }
                                    } else {
                                        "停止失败".showToast(context, ToastType.ERROR)
                                    }
                                }
                            }
                        )
                    }

                    Spacer(modifier = Modifier.height(12.dp))

                    when (it) {
                        AudioRecordHelper.RecordState.IDLE -> Text("点击开始录音",color = White, fontSize = 14.sp, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400))
                        AudioRecordHelper.RecordState.RECORDING -> Text("", fontSize = 14.sp, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400))
                        AudioRecordHelper.RecordState.PAUSED -> Text("需要至少3s请继续录音",color = White, fontSize = 14.sp, style = NO_PADDING_TEXT_STYLE.copy(fontWeight = FontWeight.W400))
                    }
                }
            }
        }
    }
}