package com.example.mobile_app_kotlin.service.repository.remote

import android.content.Context
import com.example.mobile_app_kotlin.R
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import okhttp3.Request
import java.security.KeyStore
import java.security.cert.CertificateFactory
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

class RetrofitClient private constructor() {

    companion object {

        private lateinit var INSTANCE: Retrofit
        private var token: String = ""

        private fun getRetrofitInstance(context: Context): Retrofit {
//            val certInputStream = context.resources.openRawResource(R.raw.certificado_ssl)
//            val certFactory = CertificateFactory.getInstance("X.509")
//            val cert = certFactory.generateCertificate(certInputStream)
//            certInputStream.close()

//            val certInputStream = context.resources.openRawResource(R.raw.certificado_ssl)
//            val certificate = CertificateFactory.getInstance("X.509").generateCertificate(certInputStream)
//            certInputStream.close()

//            val keyStore = KeyStore.getInstance(KeyStore.getDefaultType())
//            keyStore.load(null)
//            keyStore.setCertificateEntry("codelabz", certificate)

//            val keyStoreType = KeyStore.getDefaultType()
//            val keyStore = KeyStore.getInstance(keyStoreType)
//            keyStore.load(null, null)
//            keyStore.setCertificateEntry("myca", cert)

//            val trustManagerFactory = TrustManagerFactory.getInstance(
//                TrustManagerFactory.getDefaultAlgorithm()
//            )
//            trustManagerFactory.init(keyStore)

//            val sslContext = SSLContext.getInstance("TLS")
//            sslContext.init(null, trustManagerFactory.trustManagers, null)
//            val sslSocketFactory = sslContext.socketFactory

            val httpClient = OkHttpClient.Builder()
//                .sslSocketFactory(sslSocketFactory, trustManagerFactory.trustManagers[0] as X509TrustManager)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept", "application/json, text/plain, */*")
                        .addHeader("Content-Type", "application/json")
                        .addHeader("Accept-Encoding", "gzip, deflate, br")
//                        .addHeader("Connection", "keep-alive")
                        .addHeader("keep-alive", "timeout=60")
                        .build()
                    chain.proceed(request)
                }
                .callTimeout(30, TimeUnit.SECONDS)
                .build()

            if (!::INSTANCE.isInitialized) {
                INSTANCE = Retrofit.Builder()
                    .baseUrl("http://10.18.7.76:8080/")
                    .client(httpClient)
                    .addConverterFactory(GsonConverterFactory.create())
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
