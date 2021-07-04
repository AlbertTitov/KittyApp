package com.example.module_base_sdk.viewmodel

import androidx.lifecycle.MutableLiveData

/**
 * Класс оборачивающий [MutableLiveData], который позволяет закрыть доступ к методам [MutableLiveData] вне классов реализующих [LiveDataViewModel]
 */
class LiveDataProxy<T> internal constructor(internal val liveDataInternal: MutableLiveData<T>)