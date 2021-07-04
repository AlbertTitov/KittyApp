package com.example.feature_cats_feed.di

import com.example.feature_cats_feed.impl.view_model.UpdatedCatInteractor
import com.example.module_base_sdk.PerFeature
import dagger.Module
import dagger.Provides


@Module
class CatsModule {

    @Provides
    @PerFeature
    fun catsFavouritesInteractor() = UpdatedCatInteractor
}