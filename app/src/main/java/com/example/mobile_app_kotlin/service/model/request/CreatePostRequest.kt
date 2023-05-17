package com.example.mobile_app_kotlin.service.model.request

data class CreatePostRequest(
    val title: String,
    val content: String,
    val idUser: Int,
    val idTopic: Int
)
