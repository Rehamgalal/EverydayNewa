package com.example.everydaynewa.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetroInstance {

    companion object {
       const val baseURL = "https://newsapi.org/"
        val requestInterceptor = Interceptor {
            chain ->
            val url = chain.request()
                .url
                .newBuilder()
                .addQueryParameter("apiKey", "3b791b99e76d42929fff2fe11ecb5608")
                .build()
            val request = chain.request()
                .newBuilder()
                .url(url)
                .build()
            return@Interceptor chain.proceed(request)
        }

        private val client = OkHttpClient.Builder()
            .addInterceptor(requestInterceptor)
            .connectTimeout(60,TimeUnit.SECONDS)
            .build()

        fun getRetrofitInstance(): RetrofitApi {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(RetrofitApi::class.java)
        }
    }
}