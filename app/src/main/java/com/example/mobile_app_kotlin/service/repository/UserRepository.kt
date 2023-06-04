package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.UserUpdateRequest
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.model.response.UserProfileModel
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient
import com.example.mobile_app_kotlin.service.repository.remote.UserService

class UserRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(UserService::class.java, context)

    fun getUserProfile(idUser: Int, listener: APIListener<UserProfileModel>) {
//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.getUserProfile(idUser), listener)
    }

    fun updateUserProfile(idUser: Int, userUpdateRequest: UserUpdateRequest, listener: APIListener<UserProfileModel>) {
//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.updateUserProfile(idUser, userUpdateRequest), listener)
    }
}
