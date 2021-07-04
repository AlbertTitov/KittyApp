package com.example.data.di

import com.example.data.domain.CatsUseCases
import com.example.data.domain.CatsUseCasesImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal class DomainModule {

    @Provides
    @Singleton
    fun catsDomain() : CatsUseCases = CatsUseCasesImpl
}