package com.example.module_base_sdk.viewmodel

import androidx.lifecycle.MutableLiveData

/**
 * Интерфейс с екстеншенами, который открывает доступ к изменению [MutableLiveData] внутри [LiveDataProxy]
 */
interface LiveDataViewModel {

    /**
     * @param initialValue - изначальное значение состояния
     * @return Создает [LiveDataProxy] c [MutableLiveData]
     */
    fun <T> state(initialValue: T? = null): LiveDataProxy<T> = LiveDataProxy<T>(MutableLiveData()).apply {
        if (initialValue != null) postValue(initialValue)
    }

    /**
     * @return Создает [LiveDataProxy] c [OneShotLiveData]
     */
    fun <T> command(): LiveDataProxy<T> = LiveDataProxy(OneShotLiveData())

    private val <T> LiveDataProxy<T>.liveData: MutableLiveData<T>
        get() = this.liveDataInternal

    /**
     * Устанавливает значение в [MutableLiveData] через метод [androidx.lifecycle.MutableLiveData.postValue]
     * @param value - значение
     */
    fun <T> LiveDataProxy<T>.postValue(value: T) = this.liveData.postValue(value)

    /**
     * Возвращает текущее значение [androidx.lifecycle.LiveData] через [androidx.lifecycle.LiveData.getValue]
     */
    val <T> LiveDataProxy<T>.value: T?
        get() = this.liveData.value

}

