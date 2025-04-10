package com.ethan.compose.utils

import android.Manifest
import com.blankj.utilcode.util.PermissionUtils
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

object MyPermissionUtils {

    suspend fun checkRecordPermission(
        onlyCheck: Boolean = true,
        jump2Setting: Boolean = false,
    ) = suspendCoroutine { suspendCoroutine ->
        val photoPermission = listOf(Manifest.permission.RECORD_AUDIO).toTypedArray()
        val granted = PermissionUtils.isGranted(*photoPermission)
        if (granted) {
            suspendCoroutine.resume(true)
        } else {
            if (onlyCheck) {
                suspendCoroutine.resume(false)
                return@suspendCoroutine
            }

            PermissionUtils.permission(*photoPermission)
                .callback { isAllGranted, _, deniedForever, _ ->
                    suspendCoroutine.resume(isAllGranted)
                    if (jump2Setting) {
                        if (deniedForever.isNotEmpty()) PermissionUtils.launchAppDetailsSettings()
                    }
                }.request()
        }
    }

}