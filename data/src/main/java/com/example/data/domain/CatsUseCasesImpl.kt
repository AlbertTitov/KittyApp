package com.example.data.domain

import com.example.data.domain.model.CatEntity
import com.example.data.repository.db.model.CatDbModel
import com.example.data.repository.remote.model.CatDto

internal object CatsUseCasesImpl : CatsUseCases {

    override fun mapCatDtoToDomain(
        catDtoList: List<CatDto>,
        favouriteCats: List<CatDbModel>
    ) = catDtoList.mapNotNull { cat ->

        if (cat.id != null && cat.url != null) {
            CatEntity(
                id = cat.id,
                url = cat.url,
                isFavourite = checkCatIsFavourite(cat.id, favouriteCats)
            )
        } else {
            null
        }
    }

    override fun mapCatDomainToDb(cat: CatEntity) = cat.run {
        CatDbModel(
            id = id,
            url = url,
            isFavourite = isFavourite
        )
    }

    override fun mapCatDbToDomain(cat: CatDbModel) = cat.run {
        CatEntity(
            id = id,
            url = url,
            isFavourite = isFavourite
        )
    }

    private fun checkCatIsFavourite(
        catId: String,
        favouriteCats: List<CatDbModel>
    ) = favouriteCats.firstOrNull { it.id == catId } != null
}