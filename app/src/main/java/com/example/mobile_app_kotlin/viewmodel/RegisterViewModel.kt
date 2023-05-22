package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.model.response.ValidationModel
import com.example.mobile_app_kotlin.service.repository.LoginRepository
import com.example.mobile_app_kotlin.service.repository.SecurityPreferences

class RegisterViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = LoginRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _user = MutableLiveData<ValidationModel>()
    val user: LiveData<ValidationModel> = _user

    fun create(email: String, password: String, name: String, nickname: String) {
        userRepository.createUser(email, password, name, nickname, object : APIListener<UserModel>{
            override fun onSuccess(result: UserModel) {
//                securityPreferences.store(CodeConstants.SHARED.TOKEN_KEY, result.token)
//                securityPreferences.store(TaskConstants.SHARED.PERSON_KEY, result.personKey)
//                securityPreferences.store(TaskConstants.SHARED.PERSON_NAME, result.name)

//                RetrofitClient.addHeaders(result.token, result.personKey)

                _user.value = ValidationModel()
            }
            override fun onFailure(message: String) {
                _user.value = ValidationModel(message)
            }
        })
    }

}