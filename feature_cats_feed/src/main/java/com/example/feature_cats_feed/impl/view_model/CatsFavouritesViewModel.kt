package com.example.feature_cats_feed.impl.view_model

import com.example.data.domain.model.CatEntity
import com.example.data.repository.CatsRepository
import com.example.feature_cats_feed.impl.view_model.base.BaseCatsFeedViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CatsFavouritesViewModel @Inject constructor(
    repository: CatsRepository,
    updatedListInteractor: UpdatedCatInteractor
) : BaseCatsFeedViewModel(
    repository,
    updatedListInteractor
) {

    fun removeCatFromFavourite(cat: CatEntity) =
        repository.removeCatFromFavourite(cat.id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onComplete = {
                    val newCat = cat.copy(isFavourite = false)
                    postUpdatedCat(newCat)
                },
                onError = { errorCommand.postValue(it) }
            )
            .untilClear()

    override fun requestCatsList(page: Int, requestLimit: Int) =
        repository.getFavouriteCats(page, requestLimit)

    override fun handleUpdatedCat(newCat: CatEntity) = catsState.value?.let { list ->

        val updatedList = if (newCat.isFavourite) {
            list.toMutableList().apply {
                /**  Добавляемые в "Избранное" item'ы увидим только в конце списка **/
                if (size + 1 == repository.getFavouriteCatsCount()) {
                    add(newCat)
                    offset++
                }
            }
        } else {
            val removedItem = list.firstOrNull { it.id == newCat.id }
            val index = list.indexOf(removedItem)
            if (index != -1) offset--

            list.toMutableList().apply {
                if (index != -1) {
                    removeAt(index)
                }
            }
        }

        catsList.run {
            clear()
            addAll(updatedList)
        }
        catsState.postValue(updatedList)
    }
}