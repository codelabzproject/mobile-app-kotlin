package com.example.mobile_app_kotlin.service.repository.remote

import android.content.Context
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class RetrofitClient private constructor() {

    companion object {

        private lateinit var INSTANCE: Retrofit
        private var token: String = ""

        private fun getRetrofitInstance(context: Context): Retrofit {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "*/*, text/plain, application/json")
                        .addHeader("Accept-Encoding", "gzip, deflate, br")
                        .addHeader("keep-alive", "timeout=60")
                        .build()
                    chain.proceed(request)
                }
                .callTimeout(30, TimeUnit.SECONDS)
                .build()

            val gson = GsonBuilder()
                .setLenient()
                .create()

            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl("http://192.168.15.7:8080/")
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            }
            return INSTANCE
        }

        fun <T> getService(serviceClass: Class<T>, context: Context): T {
            return getRetrofitInstance(context).create(serviceClass)
        }

        fun addHeaders(tokenValue: String) {
            token = tokenValue
        }
    }
}
