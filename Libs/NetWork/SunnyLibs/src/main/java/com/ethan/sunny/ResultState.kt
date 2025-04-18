package com.ethan.sunny

import androidx.lifecycle.MutableLiveData

/**
 * 自定义结果集封装类
 */
sealed class ResultState<out T> {
    companion object {
        fun <T> onAppSuccess(data: T): ResultState<T> =
            Success(data)

        fun <T> onAppLoading(loadingMessage: String): ResultState<T> =
            Loading(loadingMessage)

        fun <T> onAppError(error: ApiException): ResultState<T> =
            Error(error)
    }

    data class Loading(val loadingMessage: String) : ResultState<Nothing>()
    data class Success<out T>(val data: T) : ResultState<T>()
    data class Error(val error: ApiException) : ResultState<Nothing>()
}

/**
 * 处理返回值
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: DataResult<T>) {
    value = when {
        result.isSuccess() -> {
            ResultState.onAppSuccess(
                result.Data()
            )
        }
        else -> {
            ResultState.onAppError(
                ApiException(result.getMsg(), 1)
            )
        }
    }
}

/**
 * 不处理返回值 直接返回请求结果
 * @param result 请求结果
 */
fun <T> MutableLiveData<ResultState<T>>.paresResult(result: T) {
    value = ResultState.onAppSuccess(result)
}