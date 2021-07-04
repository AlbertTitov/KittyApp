package com.example.data.di

import com.example.data.repository.remote.api.CatsApi
import com.example.data.repository.remote.api.CatsRemoteRepository
import com.example.data.repository.remote.impl.CatsRemoteRepositoryImpl
import com.example.data.repository.remote.impl.CatsRetrofitProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class RemoteModule {

    @Provides
    @Singleton
    fun catsApi() : CatsApi = CatsRetrofitProvider().get().create(CatsApi::class.java)

    @Provides
    @Singleton
    fun catsRemoteRepository(api: CatsApi) : CatsRemoteRepository = CatsRemoteRepositoryImpl(api)
}