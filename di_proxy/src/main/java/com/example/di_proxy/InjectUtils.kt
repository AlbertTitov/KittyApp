package com.example.di_proxy

import android.content.Context

object InjectUtils {

    fun provideBaseComponent(applicationContext: Context): CatsAppComponent {
        return if (applicationContext is CatsAppComponentProvider) {
            (applicationContext as CatsAppComponentProvider).provideBaseComponent()
        } else {
            throw IllegalStateException("Provide the application context which implement CatsAppComponentProvider")
        }
    }
}