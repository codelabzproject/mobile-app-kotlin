package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.repository.SecurityPreferences

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _name = MutableLiveData<String>()
    val name: LiveData<String> = _name

    fun loadUserName() {
        _name.value = securityPreferences.get(CodeConstants.SHARED.USER_NAME)
    }

}