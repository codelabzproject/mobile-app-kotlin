package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.request.CreatePostRequest
import com.example.mobile_app_kotlin.service.model.request.UserRequest
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<PostModel>>

    @POST("posts")
    fun createPost(
        @Body createPostRequest: CreatePostRequest,
    ): Call<PostModel>

}