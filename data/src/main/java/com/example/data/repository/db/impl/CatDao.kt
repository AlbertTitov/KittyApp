package com.example.data.repository.db.impl

import androidx.room.*
import com.example.data.repository.db.CATS_TABLE_NAME
import com.example.data.repository.db.api.CatsCache
import com.example.data.repository.db.model.CatDbModel
import io.reactivex.Single

@Dao
internal interface CatsDao : CatsCache {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(cat: CatDbModel): Long

    @Update
    fun update(cat: CatDbModel)

    @Transaction
    override fun saveCatToFavourite(cat: CatDbModel) {
        if (insert(cat) == -1L) update(cat)
    }

    @Query("SELECT * from $CATS_TABLE_NAME")
    override fun getFavouriteCats(): Single<List<CatDbModel>>

    /** Получение списка избранных котов в диапазоне limit начиная с позиции offset **/
    @Query("SELECT * from $CATS_TABLE_NAME LIMIT :limit OFFSET :offset")
    override fun getFavouriteCats(offset: Int, limit: Int): Single<List<CatDbModel>>

    @Query("DELETE FROM $CATS_TABLE_NAME WHERE id = :catId")
    override fun removeCatFromFavourite(catId: String)

    @Query("SELECT COUNT(*) FROM $CATS_TABLE_NAME")
    override fun getFavouriteCatsCount(): Int
}
