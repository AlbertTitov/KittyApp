package com.example.kittyapp

import android.app.Application
import com.example.di_proxy.AppModule
import com.example.di_proxy.CatsAppComponent
import com.example.di_proxy.CatsAppComponentProvider
import com.example.di_proxy.DaggerCatsAppComponent

class KittyApplication : Application(), CatsAppComponentProvider {

    private lateinit var appComponent: CatsAppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = createAppComponent()
    }

    private fun createAppComponent(): CatsAppComponent = DaggerCatsAppComponent
        .builder()
        .appModule(AppModule(this))
        .build()

    override fun provideBaseComponent(): CatsAppComponent {
        return appComponent
    }
}