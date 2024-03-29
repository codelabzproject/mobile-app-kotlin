package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.AvatarUserRequest
import com.example.mobile_app_kotlin.service.model.request.UserRequest
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.repository.remote.LoginService
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient

class LoginRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(LoginService::class.java, context)

    fun loginUser(email: String, password: String, listener: APIListener<UserModel>) {
        val userRequest = UserRequest(email, password)
//        val userRequest = UserRequest("lucas.lacerda@bandtec.com.br", "12345678")

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.loginUser(userRequest), listener)
    }

    fun createUser(
        email: String,
        password: String,
        name: String,
        nickname: String,
        listener: APIListener<UserModel>
    ) {
//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }
        val userRequest = UserRequest(email, password, name, nickname)

        executeCall(remote.createUser(userRequest), listener)
    }

    fun recoveryPassword(email: String, listener: APIListener<String>) {
//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        val request = mapOf("email" to email)
        executeCall(remote.recoveryPassword(request), listener)
    }

    fun updateAvatarUser(idUser: Int, avatarUserRequest: AvatarUserRequest, listener: APIListener<Void>) {
//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.updateAvatarUser(idUser, avatarUserRequest), listener)
    }

}