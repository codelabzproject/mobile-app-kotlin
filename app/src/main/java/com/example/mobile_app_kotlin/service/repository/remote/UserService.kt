package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.request.UserRequest
import com.example.mobile_app_kotlin.service.model.request.UserUpdateRequest
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.model.response.UserProfileModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @GET("users/profile/{id}")
    fun getUserProfile(
        @Path(value = "id", encoded = true) idUser: Int
    ): Call<UserProfileModel>

    @PUT("users/mobile/{id}")
    fun updateUserProfile(
        @Path(value = "id", encoded = true) idUser: Int,
        @Body userRequest: UserUpdateRequest,
    ): Call<UserProfileModel>


}