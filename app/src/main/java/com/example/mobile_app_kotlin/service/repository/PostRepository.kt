package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.CreatePostRequest
import com.example.mobile_app_kotlin.service.model.response.PostExpandedModel
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.RiseModel
import com.example.mobile_app_kotlin.service.repository.remote.PostService
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient
import retrofit2.Callback
import retrofit2.Response

class PostRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(PostService::class.java, context)

    fun getPosts(idUser: Int, listener: APIListener<List<PostModel>>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.getPosts(idUser), listener)
    }

    fun createPost(
        createPostRequest: CreatePostRequest,
        listener: APIListener<PostModel>,
    ) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.createPost(createPostRequest), listener)
    }

    fun getPostById(idPost: Int, listener: APIListener<PostExpandedModel>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.getPostById(idPost), listener)
    }

    fun setLikePost(idPost: Int, idUser: Int, listener: APIListener<RiseModel>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.likePost(idPost, idUser), listener)
    }
}