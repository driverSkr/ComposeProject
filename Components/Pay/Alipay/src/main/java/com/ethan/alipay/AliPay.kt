package com.ethan.alipay

import android.app.Activity
import android.text.TextUtils
import androidx.annotation.WorkerThread
import com.alipay.sdk.app.PayTask


object AliPay {
    /**
     * 支付宝一次性支付
     */
    @WorkerThread
    fun alipay(body: String?, activity: Activity?, callback: OnAliPayResultCallback) {
        val alipay = PayTask(activity)
        //true: 支付宝客户端唤起之前弹出loading弹窗
        val result = alipay.payV2(body, true)
        val status = result["resultStatus"]
        //val res = result["result"]
        //val memo = result["memo"]
        if (!TextUtils.isEmpty(status) && status == "9000") {
            callback.onSuccess()
        } else if (!TextUtils.isEmpty(status) && status == "6001") {
            callback.onCancel()
        } else {
            callback.onFailure(status)
        }
    }

    /**
     * 支付并签约
     */

}