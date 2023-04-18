package com.example.mobile_app_kotlin.service.model.request

data class UserRequest(
    val email: String,
    val password: String,
    var name: String? = null,
    var nickname: String? = null,
)
