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

                if (response.code() == CodeConstants.HTTP.SUCCESS || response.code() == CodeConstants.HTTP.CREATED || response.code() == CodeConstants.HTTP.EMPTY) {
                    response.body()?.let { listener.onSuccess(it) }
                } else if (response.code() == CodeConstants.HTTP.BADREQUEST) {
                    Toast.makeText(context, context.getString(R.string.verify_infos_request), Toast.LENGTH_SHORT)
                        .show()
                } else {
//                    listener.onFailure(failResponse(response.message()))
//                    listener.onFailure(failResponse(response.errorBody()!!.string()))
                    Toast.makeText(context, context.getString(R.string.error_intern), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                Toast.makeText(context, context.getString(R.string.error_in_servidor), Toast.LENGTH_SHORT).show()
                listener.onFailure(context.getString(R.string.ERROR_UNEXPECTED))
            }
        })
    }


}