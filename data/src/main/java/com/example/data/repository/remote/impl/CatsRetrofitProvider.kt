package com.example.data.repository.remote.impl

import com.example.data.repository.remote.api.*
import com.example.data.repository.remote.api.CATS_API_KEY_HEADER_NAME
import com.example.data.repository.remote.api.CATS_API_KEY_HEADER_VALUE
import com.example.data.repository.remote.api.CATS_BASE_URL
import com.example.data.repository.remote.api.RetrofitProviderBase
import com.google.gson.Gson
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit


internal class CatsRetrofitProvider : RetrofitProviderBase() {

    private val gson = Gson()

    override fun get(): Retrofit = createDefaultRetrofit(
        baseUrl = CATS_BASE_URL,
        client = createClient(),
        gson = gson
    )

    private fun createClient(): OkHttpClient = OkHttpClient.Builder()
        .addHeader(CATS_API_KEY_HEADER_NAME, CATS_API_KEY_HEADER_VALUE)
        .addLogs()
        .connectTimeout(CATS_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(CATS_CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        .build()
}
