package com.example.data.domain

import com.example.data.repository.remote.model.CatDto
import com.example.data.domain.model.CatEntity
import com.example.data.repository.db.model.CatDbModel

interface CatsUseCases {

    fun mapCatDtoToDomain(
        catDtoList: List<CatDto>,
        favouriteCats: List<CatDbModel>
    ): List<CatEntity>

    fun mapCatDomainToDb(cat: CatEntity): CatDbModel

    fun mapCatDbToDomain(cat: CatDbModel): CatEntity
}