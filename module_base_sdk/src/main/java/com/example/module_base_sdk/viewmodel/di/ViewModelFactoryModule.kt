package com.example.module_base_sdk.viewmodel.di

import androidx.lifecycle.ViewModelProvider
import com.example.module_base_sdk.PerFeature
import dagger.Binds
import dagger.Module

/**
 * Переиспользуемый модуль для инициализации [DaggerViewModelFactory]
 * Должен использоваться во всех компонентах, где есть View Model
 */
@Module
abstract class ViewModelFactoryModule {
    @Binds
    @PerFeature
    abstract fun bindViewModelFactory(viewModelFactory: DaggerViewModelFactory): ViewModelProvider.Factory
}