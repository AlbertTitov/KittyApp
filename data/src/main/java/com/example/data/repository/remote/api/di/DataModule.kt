package com.example.data.repository.remote.api.di

import com.example.data.di.CacheModule
import com.example.data.di.DomainModule
import com.example.data.di.RemoteModule
import com.example.data.domain.CatsUseCases
import com.example.data.repository.CatsRepository
import com.example.data.repository.CatsRepositoryImpl
import com.example.data.repository.db.api.CatsCache
import com.example.data.repository.remote.api.CatsRemoteRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(
    includes = [DomainModule::class, RemoteModule::class, CacheModule::class]
)
class DataModule {

    @Provides
    @Singleton
    fun catsData(
        remoteRepository: CatsRemoteRepository,
        cache: CatsCache,
        useCases: CatsUseCases
    ) : CatsRepository = CatsRepositoryImpl(
        remoteRepository,
        cache,
        useCases
    )
}