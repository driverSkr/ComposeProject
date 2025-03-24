package com.ethan.flowbus

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlin.collections.set

object FlowBus {

    private val busMap = mutableMapOf<String, EventBus<*>>()
    private val busStickMap = mutableMapOf<String, StickEventBus<*>>()
    private val mainScope = MainScope()


    const val BUS_TEMPLATE_COMPLETED = "BUS_TEMPLATE_COMPLETED"
    const val BUS_TEMPLATE_ERROR = "BUS_TEMPLATE_ERROR"
    const val BUS_BASE_ACTIVITY = "baseActivity"
    const val BUS_WORK_UPDATE = "BUS_WORK_UPDATE"
    const val BUS_UPLOAD_POINTS_DATA = "BUS_UPLOAD_POINTS_DATA"
    const val EVEN_SETTING_LANGUAGE = "EVEN_SETTING_LANGUAGE"
    const val EVEN_TAB_INDEX = "EVEN_TAB_INDEX"
    const val EVEN_GOTO_WORK = "EVEN_GOTO_WORK"
    const val EVEN_UPDATE_MUSIC_USE = "EVEN_UPDATE_MUSIC_USE"


    @Synchronized
    fun <T> with(key: String): EventBus<T> {
        var eventBus = busMap[key]
        if (eventBus == null) {
            eventBus = EventBus<T>(key)
            busMap[key] = eventBus
        }
        return eventBus as EventBus<T>
    }

    @Synchronized
    fun <T> withStick(key: String): StickEventBus<T> {
        var eventBus = busStickMap[key]
        if (eventBus == null) {
            eventBus = StickEventBus<T>(key)
            busStickMap[key] = eventBus
        }
        return eventBus as StickEventBus<T>
    }

    // 真正实现类
    open class EventBus<T>(private val key: String) : LifecycleObserver {

        // 私有对象用于发送消息
        private val _events: MutableSharedFlow<T> by lazy {
            obtainEvent()
        }

        // 暴露的公有对象用于接收消息
        val events = _events.asSharedFlow()

        open fun obtainEvent(): MutableSharedFlow<T> =
            MutableSharedFlow(0, 5, BufferOverflow.SUSPEND)

        // 主线程接收数据
        fun register(lifecycleOwner: LifecycleOwner, action: (t: T) -> Unit) {
            lifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
                if (event == Lifecycle.Event.ON_DESTROY) {
                    val subscriptCount = _events.subscriptionCount.value
                    if (subscriptCount <= 0) {
                        busMap.remove(key)
                    }
                }
            })
            lifecycleOwner.lifecycleScope.launch {
                events.collect {
                    try {
                        action(it)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }

        // 协程中发送数据
        suspend fun postCurrentSp(event: T) {
            _events.emit(event)
        }

        // 主线程发送数据
        fun postMain(event: T) {
            mainScope.launch {
                _events.emit(event)
            }
        }

    }

    class StickEventBus<T>(key: String) : EventBus<T>(key) {
        override fun obtainEvent(): MutableSharedFlow<T> =
            MutableSharedFlow(1, 1, BufferOverflow.DROP_OLDEST)
    }

    class FlowMessage<T> {
        var key: String? = null
        var data: T? = null
    }
}