package com.example.di_proxy

import com.example.data.repository.CatsRepository
import com.example.data.repository.remote.api.di.DataModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DataModule::class]
)
interface CatsAppComponent {

    fun getCatsRepository() : CatsRepository
}