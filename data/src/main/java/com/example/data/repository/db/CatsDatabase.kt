package com.example.data.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.repository.db.model.CatDbModel
import com.example.data.repository.db.impl.CatsDao

@Database(entities = [CatDbModel::class], version = DATABASE_VERSION, exportSchema = EXPORT_SCHEMA)
internal abstract class CatsDatabase : RoomDatabase() {

    abstract val catsDao: CatsDao

    companion object {
        @Volatile
        private var INSTANCE: CatsDatabase? = null

        internal fun getInstance(context: Context) : CatsDatabase {

            var instance = INSTANCE

            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatsDatabase::class.java,
                    CATS_DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()

                INSTANCE = instance
            }

            return instance
        }
    }
}