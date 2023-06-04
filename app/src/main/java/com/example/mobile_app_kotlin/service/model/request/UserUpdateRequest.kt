package com.example.mobile_app_kotlin.service.model.request

data class UserUpdateRequest(
    val name: String? = null,
    val nickname: String? = null,
    var about: String? = null,
)