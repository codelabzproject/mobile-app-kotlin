package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.Toast
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
                if (response.isSuccessful) {

                    if (response.code() == CodeConstants.HTTP.SUCCESS || response.code() == CodeConstants.HTTP.CREATED) {
                        response.body()?.let { listener.onSuccess(it) }
                    } else if (response.code() == CodeConstants.HTTP.BADREQUEST) {
                        Toast.makeText(context, "Verifique as informações", Toast.LENGTH_SHORT)
                            .show()
                    } else {
//                    listener.onFailure(failResponse(response.message()))
//                    listener.onFailure(failResponse(response.errorBody()!!.string()))
                        Toast.makeText(context, "Erro interno", Toast.LENGTH_SHORT).show()

                    }
                } else {
                    val errorBody = response.errorBody()
                    if (errorBody != null) {
                        val errorString = errorBody.string()
                        listener.onFailure(errorString)
                    } else {
                        listener.onFailure("Error body is null")
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }


}