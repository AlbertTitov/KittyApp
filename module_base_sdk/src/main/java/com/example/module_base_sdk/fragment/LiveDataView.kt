package com.example.module_base_sdk.fragment

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.module_base_sdk.viewmodel.LiveDataProxy

/**
 * Интерфейс с екстеншенами, который открывает доступ к чтению [androidx.lifecycle.LiveData] внутри [LiveDataProxy] через [bindTo] метод
 */
interface LiveDataView {

    /**
     * [LifecycleOwner] который будет использоваться для подписки на [LiveData]
     */
    val extensionsLifecycleOwner: LifecycleOwner

    private val <T> LiveDataProxy<T>.liveData: LiveData<T>
        get() = this.liveDataInternal

    /**
     * Позволяет обзервить [androidx.lifecycle.LiveData] внутри [LiveDataProxy] (вызывает [androidx.lifecycle.LiveData.observe] используя [extensionsLifecycleOwner])
     * @param consumer будет получать события изменения данных
     */
    infix fun <T> LiveDataProxy<T>.bindTo(consumer: ((T) -> Unit)) = this.liveData.observe(extensionsLifecycleOwner, Observer(consumer))

    infix fun LiveDataProxy<Unit>.bindTo(consumer: (() -> Unit)) =
            this.liveData.observe(extensionsLifecycleOwner, { consumer.invoke() })

}