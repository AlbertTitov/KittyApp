package com.example.feature_cats_feed.di

import androidx.lifecycle.ViewModel
import com.example.feature_cats_feed.impl.view_model.CatsFavouritesViewModel
import com.example.feature_cats_feed.impl.view_model.CatsFeedViewModel
import com.example.module_base_sdk.viewmodel.di.ViewModelFactoryModule
import com.example.module_base_sdk.viewmodel.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module(includes = [ViewModelFactoryModule::class])
abstract class CatsViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatsFeedViewModel::class)
    abstract fun bindCatsFeedViewModel(viewModel: CatsFeedViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CatsFavouritesViewModel::class)
    abstract fun bindCatsFavouritesViewModel(viewModel: CatsFavouritesViewModel): ViewModel
}
