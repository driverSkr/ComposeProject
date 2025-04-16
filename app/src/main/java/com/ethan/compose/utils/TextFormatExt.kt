package com.ethan.compose.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.round

/** 时分秒 00:00:00 */
@SuppressLint("DefaultLocale")
fun Long.formatHMSTime(): String {
    val seconds = (this / 1000) % 60
    val minutes = (this / (1000 * 60)) % 60
    val hours = ((this / (1000 * 60)) % 60) % 60
    return String.format("%02d:%02d:%02d", hours, minutes, seconds)
}

/**
 * 时分秒 00:00:00
 *  分秒 00:00
 * */
@SuppressLint("DefaultLocale")
fun Long.formatHMSTime2(): String {
    val totalSeconds = round(this / 1000.0).toLong()
    val seconds = totalSeconds % 60
    val minutes = (totalSeconds / 60) % 60
    val hours = totalSeconds / 3600

    return if (hours > 0) {
        String.format("%02d:%02d:%02d", hours, minutes, seconds)
    } else {
        String.format("%02d:%02d", minutes, seconds)
    }
}

/** 分秒毫秒 00:00:00 */
@SuppressLint("DefaultLocale")
fun Long.formatMSCTime(): String {
    val totalSeconds = this / 1000
    val minutes = totalSeconds / 60          // 计算分钟
    val seconds = totalSeconds % 60          // 计算秒
    val centiSeconds = (this % 1000) / 10  // 取毫秒的前两位（即十毫秒）

    // 格式化为 "00:00:00" 格式（分钟:秒:十毫秒）
    return String.format("%02d:%02d:%02d", minutes, seconds, centiSeconds)
}

fun getAudioName(): String {
    val dateFormat = SimpleDateFormat("HHmmss_dd_MM_yyyy", Locale.getDefault())
    return "Audio_${dateFormat.format(Date())}.m4a"
}