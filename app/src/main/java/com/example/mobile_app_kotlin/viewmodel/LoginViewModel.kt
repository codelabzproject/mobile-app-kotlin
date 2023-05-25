package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.service.model.response.ValidationModel
import com.example.mobile_app_kotlin.service.repository.LoginRepository
import com.example.mobile_app_kotlin.service.repository.SecurityPreferences
import com.example.mobile_app_kotlin.service.repository.TopicRepository

class LoginViewModel(application: Application) : AndroidViewModel(application) {

    private val userRepository = LoginRepository(application.applicationContext)
    private val topicRepository = TopicRepository(application.applicationContext)
    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _login = MutableLiveData<ValidationModel>()
    val login: LiveData<ValidationModel> = _login

    private val _loggedUser = MutableLiveData<Boolean>()
    val loggedUser: LiveData<Boolean> = _loggedUser

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    fun doLogin(email: String, password: String) {

        userRepository.loginUser(email, password, object : APIListener<UserModel> {
            override fun onSuccess(result: UserModel) {
                securityPreferences.store(CodeConstants.SHARED.USER_NAME, result.name)
                securityPreferences.store(CodeConstants.SHARED.USER_AVATAR, result.avatar)
                securityPreferences.store(CodeConstants.SHARED.NICK_NAME, result.nickname)
                securityPreferences.store(CodeConstants.SHARED.USER_ID, result.idUser.toString())

                _login.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _login.value = ValidationModel(message)
            }

        })
    }

    fun loadUserName() {
        _name.value = securityPreferences.get(CodeConstants.SHARED.NICK_NAME)
    }

    fun loadUserIdLogged(): Int {
        return securityPreferences.get(CodeConstants.SHARED.USER_ID).toInt()
    }


//    fun verifyLoggedUser() {
//        val token = securityPreferences.get(CodeConstants.SHARED.TOKEN_KEY)

//        RetrofitClient.addHeaders(token)

        // Se token e person key forem diferentes de vazio, usuário está logado
//        val logged = (token != "")
//        _loggedUser.value = logged

        // Se usuário não estiver logado, aplicação vai atualizar os dados
//        if (!logged) {
//            topicRepository.getTopics(object : APIListener<List<TopicModel>> {
//                override fun onSuccess(result: List<TopicModel>) {
//                    topicRepository.saveTopics(result)
//                }

//                override fun onFailure(message: String) {
//                    val s = ""
//                }
//            }
//            )
//        }
//    }

}