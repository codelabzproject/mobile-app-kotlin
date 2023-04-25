package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.model.response.ValidationModel
import com.example.mobile_app_kotlin.service.repository.SecurityPreferences
import com.example.mobile_app_kotlin.service.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _user = MutableLiveData<UserModel>()
    val user: LiveData<UserModel> = _user

//    private val _loggedUser = MutableLiveData<Boolean>()
//    val loggedUser: LiveData<Boolean> = _loggedUser

    fun getUser(idUser: Int) {
        userRepository.getUserProfile(idUser, object : APIListener<UserModel> {
            override fun onSuccess(result: UserModel) {
                securityPreferences.store(CodeConstants.SHARED.USER_NAME, result.name)
                securityPreferences.store(CodeConstants.SHARED.USER_AVATAR, result.avatar)
                securityPreferences.store(CodeConstants.SHARED.NICK_NAME, result.nickname)

                _user.value = result
            }

            override fun onFailure(message: String) {
//                _user.value = ValidationModel(message)
            }

        })
    }
}