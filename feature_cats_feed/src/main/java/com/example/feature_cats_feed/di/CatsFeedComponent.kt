package com.example.feature_cats_feed.di

import com.example.di_proxy.CatsAppComponent
import com.example.feature_cats_feed.impl.screens.base.BaseCatsFeedFragment
import com.example.module_base_sdk.PerFeature
import dagger.Component
import java.io.Serializable


@Component(
    modules = [CatsModule::class, CatsViewModelModule::class],
    dependencies = [CatsAppComponent::class]
)
@PerFeature
interface CatsFeedComponent : Serializable {

    fun inject(catsFeedFragment: BaseCatsFeedFragment)
}