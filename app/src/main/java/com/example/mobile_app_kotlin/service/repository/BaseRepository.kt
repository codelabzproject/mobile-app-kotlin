package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseRepository(val context: Context) {

    private fun failResponse(str: String): String {
        return Gson().fromJson(str, String::class.java)
    }

    fun <T> executeCall(call: Call<T>, listener: APIListener<T>) {
        call.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if (response.code() == CodeConstants.HTTP.SUCCESS || response.code() == CodeConstants.HTTP.CREATED) {
                    response.body()?.let { listener.onSuccess(it) }
                } else {
//                    listener.onFailure(failResponse(response.message()))
//                    listener.onFailure(failResponse(response.errorBody()!!.string()))

                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }

    /**
     * Verifica se existe conexão com internet
     */
//    fun isConnectionAvailable(): Boolean {
//        var result = false
//
//        // Gerenciador de conexão
//        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//
//        // Verifica versão do sistema rodando a aplicação
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            val activeNet = cm.activeNetwork ?: return false
//            val netWorkCapabilities = cm.getNetworkCapabilities(activeNet) ?: return false
//            result = when {
//                netWorkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
//                netWorkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
//                else -> false
//            }
//        } else {
//            if (cm.activeNetworkInfo != null) {
//                result = when (cm.activeNetworkInfo!!.type) {
//                    ConnectivityManager.TYPE_WIFI -> true
//                    ConnectivityManager.TYPE_MOBILE -> true
//                    ConnectivityManager.TYPE_ETHERNET -> true
//                    else -> false
//                }
//            }
//        }
//
//        return result
//    }
}