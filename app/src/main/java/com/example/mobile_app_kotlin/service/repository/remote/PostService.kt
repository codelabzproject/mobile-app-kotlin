package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.request.CreateCommentRequest
import com.example.mobile_app_kotlin.service.model.request.CreatePostRequest
import com.example.mobile_app_kotlin.service.model.response.CommentModel
import com.example.mobile_app_kotlin.service.model.response.PostExpandedModel
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.RiseModel
import retrofit2.Call
import retrofit2.http.*

interface PostService {

    @GET("posts")
    fun getPosts(
        @Query("idUser") idUser: Int
    ): Call<List<PostModel>>

    @GET("posts/{idPost}")
    fun getPostById(
        @Path(value = "idPost", encoded = true) idPost: Int
    ): Call<PostExpandedModel>

    @POST("posts")
    fun createDiscussion(
        @Body createPostRequest: CreatePostRequest,
    ): Call<PostModel>

    @POST("posts/doubt")
    fun createDoubt(
        @Body createPostRequest: CreatePostRequest,
    ): Call<PostModel>

    @PUT("posts/rise/{idPost}/{idUser}")
    fun likePost(
        @Path("idPost", encoded = true) idPost: Int,
        @Path("idUser", encoded = true) idUser: Int,
    ): Call<RiseModel>

    @PUT("posts/comment/{idComment}/{idUser}")
    fun likeComment(
        @Path("idComment", encoded = true) idComment: Int,
        @Path("idUser", encoded = true) idUser: Int,
    ): Call<RiseModel>

    @POST("posts/comment")
    fun createComment(
        @Body createComment: CreateCommentRequest,
    ): Call<CommentModel>
}