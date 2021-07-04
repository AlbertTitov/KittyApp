package com.example.data.repository.db.api

import com.example.data.repository.db.model.CatDbModel
import io.reactivex.Single

interface CatsCache {

    /** Сохранение кота в список избранных **/
    fun saveCatToFavourite(cat: CatDbModel)

    /** Удаление кота из списка избранных **/
    fun removeCatFromFavourite(catId: String)

    /** Получение списка избранных котов **/
    fun getFavouriteCats(): Single<List<CatDbModel>>

    /** Получение списка избранных котов в диапазоне с minPosition по maxPosition **/
    fun getFavouriteCats(offset: Int, limit: Int): Single<List<CatDbModel>>

    /** Получение количества записей в таблице избранных котов **/
    fun getFavouriteCatsCount() : Int
}