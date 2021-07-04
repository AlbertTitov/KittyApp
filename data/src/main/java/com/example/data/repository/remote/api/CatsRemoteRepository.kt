package com.example.data.repository.remote.api

import com.example.data.repository.remote.model.CatDto
import io.reactivex.Single

interface CatsRemoteRepository {

    /** Получение списка избранных котов **/
    fun getCats(page: Int, limit: Int, direction: String): Single<List<CatDto>>
}