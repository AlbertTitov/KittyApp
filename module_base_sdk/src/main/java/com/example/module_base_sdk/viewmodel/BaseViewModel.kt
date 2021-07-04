package com.example.module_base_sdk.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.plusAssign

abstract class BaseViewModel : ViewModel(), LiveDataViewModel {

    private val compositeDisposable = CompositeDisposable()

    val loadingState = state<Boolean>()
    val errorCommand = command<Throwable>()

    final override fun <T> state(initialValue: T?): LiveDataProxy<T> = super.state(initialValue)

    final override fun <T> command(): LiveDataProxy<T> = super.command()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    protected fun Disposable.untilClear() {
        compositeDisposable += this
    }
}