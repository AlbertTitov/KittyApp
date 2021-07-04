package com.example.data.repository

import com.example.data.repository.remote.api.CATS_SORTING_ORDER_DESC
import com.example.data.domain.model.CatEntity
import io.reactivex.Completable
import io.reactivex.Single


interface CatsRepository {

    fun getCats(
        page: Int,
        limit: Int,
        direction: String = CATS_SORTING_ORDER_DESC
    ) : Single<List<CatEntity>>

    /** Сохранение кота в список избранных **/
    fun saveCatToFavourite(cat: CatEntity) : Completable

    /** Удаление кота из списка избранных **/
    fun removeCatFromFavourite(catId: String) : Completable

    /** Получение списка избранных котов **/
    fun getFavouriteCats() : Single<List<CatEntity>>

    /** Получение списка избранных котов начиная с позиции offset в количестве limit штук **/
    fun getFavouriteCats(offset: Int, limit: Int) : Single<List<CatEntity>>

    /** Получение количества записей в таблице избранных котов **/
    fun getFavouriteCatsCount() : Int
}