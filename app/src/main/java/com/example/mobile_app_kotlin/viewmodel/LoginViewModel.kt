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
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = UserRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = _loggedUser

    fun doLogin(email: String, password: String) {

        userRepository.getUser(email, password, object : APIListener<UserModel>{
            override fun onSuccess(result: UserModel) {
                securityPreferences.store(CodeConstants.SHARED.USER_NAME, result.name)
                securityPreferences.store(CodeConstants.SHARED.USER_AVATAR, result.avatar)

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _login.value = ValidationModel(message)
            }

        })

        userRepository.loginUser(email, password, object : APIListener<UserModel>{
            override fun onSuccess(result: UserModel) {
                securityPreferences.store(CodeConstants.SHARED.TOKEN_KEY, result.token)

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _login.value = ValidationModel(message)
            }

        })
    }

    fun verifyLoggedUser() {
        val token = securityPreferences.get(CodeConstants.SHARED.USER_NAME)

        RetrofitClient.addHeaders(token)

        // Se token e person key forem diferentes de vazio, usuário está logado
        val logged = (token != "")
        _loggedUser.value = logged

        // Se usuário não estiver logado, aplicação vai atualizar os dados
        if (!logged) {
//            priorityRepository.list(object : APIListener<List<PriorityModel>>{
//                override fun onSuccess(result: List<PriorityModel>) {
//                    priorityRepository.save(result)
//                }
//
//                override fun onFailure(message: String) {
//                }
//            })
        }
    }

}