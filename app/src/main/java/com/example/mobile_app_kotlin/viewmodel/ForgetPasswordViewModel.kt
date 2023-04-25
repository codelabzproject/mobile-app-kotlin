package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.UserRequest
import com.example.mobile_app_kotlin.service.model.response.ValidationModel
import com.example.mobile_app_kotlin.service.repository.UserRepository

class ForgetPasswordViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)

    private val _message = MutableLiveData<ValidationModel>()
    val message: LiveData<ValidationModel> = _message

    fun forgetPassword(email: String) {
        userRepository.recoveryPassword(email, object : APIListener<String> {

            override fun onSuccess(result: String) {
                _message.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _message.value = ValidationModel(message)
            }
        })
    }


}