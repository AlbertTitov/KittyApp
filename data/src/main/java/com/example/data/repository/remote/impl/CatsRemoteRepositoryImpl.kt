package com.example.data.repository.remote.impl

import com.example.data.repository.remote.api.CatsApi
import com.example.data.repository.remote.api.CatsRemoteRepository
import com.example.data.repository.remote.model.CatDto
import io.reactivex.Single

internal class CatsRemoteRepositoryImpl (
    val api: CatsApi
    ) : CatsRemoteRepository {

    override fun getCats(
        page: Int,
        limit: Int,
        direction: String
    ): Single<List<CatDto>> =
        api.getCats(
            page,
            limit,
            direction
        )
}