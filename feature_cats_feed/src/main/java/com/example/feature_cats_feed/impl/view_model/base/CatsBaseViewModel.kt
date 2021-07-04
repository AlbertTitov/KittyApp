package com.example.feature_cats_feed.impl.view_model.base

import com.example.data.domain.model.CatEntity
import com.example.data.repository.CatsRepository
import com.example.feature_cats_feed.impl.view_model.UpdatedCatInteractor
import com.example.module_base_sdk.viewmodel.BaseViewModel
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers


abstract class BaseCatsFeedViewModel(
    protected val repository: CatsRepository,
    private val updatedListInteractor: UpdatedCatInteractor
) : BaseViewModel() {

    protected val catsList = mutableListOf<CatEntity>()

    val catsState = state<List<CatEntity>>()

    protected var offset = 0

    protected abstract fun requestCatsList(
        page: Int = offset,
        requestLimit: Int = CATS_REQUEST_LIMIT
    ): Single<List<CatEntity>>

    abstract fun handleUpdatedCat(newCat: CatEntity): Unit?

    fun getCatsList() =
        requestCatsList()
            .doOnSubscribe { if (offset == 0) loadingState.postValue(true) }
            .doFinally { loadingState.postValue(false) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                catsList += it
                offset += it.size
            }
            .subscribeBy(
                onSuccess = { catsState.postValue(catsList) },
                onError = { errorCommand.postValue(it) }
            )
            .untilClear()

    fun observeUpdatedCatsList() = updatedListInteractor
        .updatedCatsList
        .subscribeBy(
            onNext = ::handleUpdatedCat,
            onError = { errorCommand.postValue(it) }
        )
        .untilClear()

    protected fun postUpdatedCat(cat: CatEntity) = updatedListInteractor.updatedCatsList.onNext(cat)

    companion object {
        @JvmStatic
        protected val CATS_REQUEST_LIMIT = 10
    }
}