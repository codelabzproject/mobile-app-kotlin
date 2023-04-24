package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.UserRequest
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.TopicModel
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.repository.remote.PostService
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(PostService::class.java)

    fun getPosts(listener: APIListener<List<PostModel>>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.getPosts(), listener)
    }
}