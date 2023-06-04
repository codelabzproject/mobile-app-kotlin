package com.example.mobile_app_kotlin.service.model.request

data class CreateCommentRequest(
    val content: String,
    val idUser: Int,
    val idPost: Int
)
