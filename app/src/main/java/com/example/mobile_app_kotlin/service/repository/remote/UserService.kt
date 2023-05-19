package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.response.UserProfileModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserService {
    @GET("users/profile/{id}")
    fun getUserProfile(
        @Path(value = "id", encoded = true) idUser: Int
    ): Call<UserProfileModel>

}