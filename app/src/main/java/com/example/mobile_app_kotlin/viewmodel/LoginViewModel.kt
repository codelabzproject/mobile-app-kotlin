package com.example.mobile_app_kotlin.viewmodel

import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.UserModel
import com.example.mobile_app_kotlin.service.repository.remote.AuthRepository

class LoginViewModel()  {

    private val authRepository = AuthRepository()

    fun doLogin(email: String, password: String) {

        authRepository.login(email, password, object : APIListener<UserModel>{
            override fun onSuccess(result: UserModel) {

            }

            override fun onFailure(message: String) {
            }

        })
//        personRepository.login(email, password, object : APIListener<UserModel> {
//            override fun onSuccess(result: UserModel) {
//                securityPreferences.store(TaskConstants.SHARED.TOKEN_KEY, result.token)
//                securityPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
//                securityPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)
//
//                RetrofitClient.addHeaders(result.token, result.personKey)
//
//                _login.value = ValidationModel()
//            }
//
//            override fun onFailure(message: String) {
//                _login.value = ValidationModel(message)
//            }
//        })
    }
}