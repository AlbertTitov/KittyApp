package com.example.data.repository

import com.example.data.domain.CatsUseCases
import com.example.data.repository.db.api.CatsCache
import com.example.data.repository.remote.api.CatsRemoteRepository
import com.example.data.domain.model.CatEntity
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class CatsRepositoryImpl @Inject constructor(
    private val remoteRepository: CatsRemoteRepository,
    private val cache: CatsCache,
    private val useCases: CatsUseCases
) : CatsRepository {

    override fun getCats(page: Int, limit: Int, direction: String) =
        Single.zip(
            remoteRepository.getCats(
                page = page,
                limit = limit,
                direction = direction
            ),
            cache.getFavouriteCats(),
            (useCases::mapCatDtoToDomain)
        )

    override fun saveCatToFavourite(cat: CatEntity) = Completable.fromAction {
        val catDbModel = useCases.mapCatDomainToDb(cat)
        cache.saveCatToFavourite(catDbModel)
    }

    override fun removeCatFromFavourite(catId: String) = Completable.fromAction {
        cache.removeCatFromFavourite(catId)
    }

    override fun getFavouriteCats(offset: Int, limit: Int) =
        cache
            .getFavouriteCats(offset, limit)
            .map { dbModels ->
                dbModels.map(useCases::mapCatDbToDomain)
            }

    override fun getFavouriteCats() =
        cache.getFavouriteCats()
            .map { dbModels ->
                dbModels.map(useCases::mapCatDbToDomain)
            }

    override fun getFavouriteCatsCount() = cache.getFavouriteCatsCount()
}