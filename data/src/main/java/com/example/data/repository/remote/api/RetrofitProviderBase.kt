package com.example.data.repository.remote.api

import com.example.data.BuildConfig
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

abstract class RetrofitProviderBase {

    abstract fun get(): Retrofit

    protected fun createDefaultRetrofit(baseUrl: String, client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()

    protected fun OkHttpClient.Builder.addLogs(): OkHttpClient.Builder = this.apply {
        if (BuildConfig.DEBUG) {
            addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
    }

    protected fun OkHttpClient.Builder.addHeader(name: String, value: String) = this.apply {
        addInterceptor { chain ->
            chain.proceed(chain.request().newBuilder()
                .addHeader(name, value)
                .build()
            )
        }
    }
}