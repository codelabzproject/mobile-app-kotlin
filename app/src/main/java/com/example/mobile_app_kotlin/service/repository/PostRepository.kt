package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.CreatePostRequest
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.repository.remote.PostService
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient
import retrofit2.Callback
import retrofit2.Response

class PostRepository(context: Context): BaseRepository(context) {

    private val remote = RetrofitClient.getService(PostService::class.java, context)

    fun getPosts(listener: APIListener<List<PostModel>>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.getPosts(), listener)
    }

    fun createPost(listener: APIListener<PostModel>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }
        val post = CreatePostRequest("Título do post", "Conteúdo do post", 123, 456)

        executeCall(remote.createPost(post), listener)
    }
}