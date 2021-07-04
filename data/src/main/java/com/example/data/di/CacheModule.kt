package com.example.data.di

import android.content.Context
import com.example.data.repository.db.api.CatsCache
import com.example.data.repository.db.CatsDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class CacheModule {

    @Provides
    @Singleton
    fun catsDatabase(context: Context) : CatsDatabase = CatsDatabase.getInstance(context)

    @Provides
    @Singleton
    fun catsCache(catsDb: CatsDatabase) : CatsCache = catsDb.catsDao
}