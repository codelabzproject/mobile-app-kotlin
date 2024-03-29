package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.UserUpdateRequest
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.model.response.UserProfileModel
import com.example.mobile_app_kotlin.service.repository.SecurityPreferences
import com.example.mobile_app_kotlin.service.repository.UserRepository

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)
    val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _user = MutableLiveData<UserProfileModel>()
    val user: LiveData<UserProfileModel> = _user
//    private val _loggedUser = MutableLiveData<Boolean>()
//    val loggedUser: LiveData<Boolean> = _loggedUser

    fun getUser(idUser: Int) {
        userRepository.getUserProfile(idUser, object : APIListener<UserProfileModel> {
            override fun onSuccess(result: UserProfileModel) {
                securityPreferences.store(CodeConstants.SHARED.USER_NAME, result.user?.name ?: "")
                securityPreferences.store(CodeConstants.SHARED.USER_AVATAR, result.user?.avatar ?: "")
                securityPreferences.store(CodeConstants.SHARED.NICK_NAME, result.user?.nickname ?: "")

                _user.value = result
            }

            override fun onFailure(message: String) {
//                _user.value = ValidationModel(message)
            }

        })
    }

    fun updateUserProfile(idUser: Int, userUpdateRequest: UserUpdateRequest) {
        userRepository.updateUserProfile(idUser, userUpdateRequest, object : APIListener<UserProfileModel> {
            override fun onSuccess(result: UserProfileModel) {
                _user.value = result
            }
            override fun onFailure(message: String) {
//                _user.value = ValidationModel(message)
            }
        })
    }
}