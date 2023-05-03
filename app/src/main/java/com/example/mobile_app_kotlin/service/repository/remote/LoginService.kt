package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.request.UserRequest
import com.example.mobile_app_kotlin.service.model.response.UserModel
import retrofit2.Call
import retrofit2.http.*

interface LoginService {

    @POST("auth/login")
    fun getUser(
        @Body userRequest: UserRequest,
    ): Call<UserModel>

    @POST("auth/login")
    fun loginUser(
        @Body userRequest: UserRequest,
    ): Call<UserModel>

    @POST("users")
    fun createUser(
        @Body userRequest: UserRequest,
    ): Call<UserModel>

    @POST("users/password-recovery")
    fun recoveryPassword(
        @Body request: Map<String, String>
    ): Call<String>
}