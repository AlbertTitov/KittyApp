package com.example.feature_cats_feed.impl.view_model

import com.example.data.domain.model.CatEntity
import com.example.data.repository.CatsRepository
import com.example.feature_cats_feed.impl.view_model.base.BaseCatsFeedViewModel
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatsFeedViewModel @Inject constructor(
    repository: CatsRepository,
    updatedListInteractor: UpdatedCatInteractor
) : BaseCatsFeedViewModel(
    repository,
    updatedListInteractor
) {

    override fun requestCatsList(page: Int, requestLimit: Int) =
        repository.getCats(page, requestLimit)

    override fun handleUpdatedCat(newCat: CatEntity) =
        getCatsUpdatedList(catsList, newCat)?.let { newList ->
            catsList.run {
                clear()
                addAll(newList)
            }
            catsState.postValue(catsList)
        }

    fun toggleCatIsFavourite(cat: CatEntity) {
        repository.getFavouriteCats()
            .flatMap { cachedCats ->
                val catIsFavourite = cachedCats.firstOrNull { cachedCat -> cachedCat.id == cat.id } != null
                val newCat = cat.copy(isFavourite = !catIsFavourite)

                catCacheTransaction(newCat).andThen(Single.just(newCat))
            }
            .subscribeOn(Schedulers.io())
            .subscribeBy(
                onSuccess = ::postUpdatedCat,
                onError = { errorCommand.postValue(it) }
            )
            .untilClear()
    }

    private fun catCacheTransaction(cat: CatEntity): Completable =
        if (cat.isFavourite) {
            repository.saveCatToFavourite(cat)
        } else {
            repository.removeCatFromFavourite(cat.id)
        }

    private fun getCatsUpdatedList(
        stateList: List<CatEntity>,
        newCat: CatEntity
    ): List<CatEntity>? {
        val oldItem = stateList.firstOrNull { it.id == newCat.id }
        val index = stateList.indexOf(oldItem)

        return if (index != -1) {
            stateList.toMutableList().apply {
                this[index] = newCat
            }
        } else {
            null
        }
    }
}