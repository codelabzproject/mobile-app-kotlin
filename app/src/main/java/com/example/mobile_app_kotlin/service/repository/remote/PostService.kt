package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.request.CreatePostRequest
import com.example.mobile_app_kotlin.service.model.request.UserRequest
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.UserModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PostService {

    @GET("posts")
    fun getPosts(): Call<List<PostModel>>

    @GET("posts/{idPost}")
    fun getPostById(
        @Path(value = "idPost", encoded = true) idPost: Int
    ): Call<PostModel>

    @POST("posts")
    fun createPost(
        @Body createPostRequest: CreatePostRequest,
    ): Call<PostModel>

}