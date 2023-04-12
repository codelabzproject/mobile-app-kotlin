package com.example.mobile_app_kotlin.service.repository.remote

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient private constructor() {

    companion object {
        private lateinit var INSTANCE: Retrofit

        private fun getRetrofitInstance(): Retrofit {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "*/*")
                        .addHeader("Accept-Encoding", "gzip, deflate, br")
                        .addHeader("Connection", "keep-alive")
                        .build()
                    chain.proceed(request)
                }

            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl("http://10.18.7.76:8080/")
                    .client(httpClient.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return INSTANCE;
        }

        fun <T> getService(serviceClass: Class<T>): T {
            return getRetrofitInstance().create(serviceClass)
        }
    }
//    companion object {
//        private lateinit var INSTANCE: Retrofit
//        private var token: String = ""
//        private var personKey: String = ""
//
//        private fun getRetrofitInstance(): Retrofit {
//            val httpClient = OkHttpClient.Builder()
//
//            httpClient.addInterceptor(object : Interceptor {
//                override fun intercept(chain: Interceptor.Chain): Response {
//                    val request = chain.request()
//                        .newBuilder()
//                        .build()
//                    return chain.proceed(request)
//                }
//            })
//
//            if (!::INSTANCE.isInitialized) {
//                synchronized(RetrofitClient::class) {
//                    INSTANCE = Retrofit.Builder()
//                        .baseUrl("http://10.18.7.76:8080/")
//                        .client(httpClient.build())
//                        .addConverterFactory(GsonConverterFactory.create())
//                        .build()
//                }
//            }
//            return INSTANCE
//        }
//
//        fun <T> getService(serviceClass: Class<T>): T {
//            return getRetrofitInstance().create(serviceClass)
//        }
//
//        fun addHeaders(tokenValue: String, personKeyValue: String) {
//            token = tokenValue
//            personKey = personKeyValue
//        }
//    }
}