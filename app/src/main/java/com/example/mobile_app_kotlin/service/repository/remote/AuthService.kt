package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.UserModel
import retrofit2.Call
import retrofit2.http.*

interface AuthService {

    @POST("auth/login")
    @Headers("Content-Type: application/json")
    fun login(
        @Body userRequest: UserRequest,
    ): Call<UserModel>

    @GET("auth/login")
    @FormUrlEncoded
    fun getPosts(
        @Field("email") email: String,
        @Field("password") password: String
    ): Call<UserModel>

}

data class UserRequest(
    val email: String,
    val password: String
)
