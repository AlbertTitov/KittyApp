package com.example.data.repository.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.data.repository.db.CATS_TABLE_NAME
import com.example.data.repository.db.IS_FAVOURITE_COLUMN_NAME

/**
 *  Добавил эту модель для работы с БД из тех соображений,
 *  что у нас доменная модель CatEntity должна быть свободна от Android sdk-зависимостей
*
* */
@Entity(tableName = CATS_TABLE_NAME)
data class CatDbModel(
    @PrimaryKey
    val id: String,
    val url: String,
    @ColumnInfo(name = IS_FAVOURITE_COLUMN_NAME)
    val isFavourite: Boolean
)