package com.example.mobile_app_kotlin.service.listener

interface PostListener {
    fun onClickPost(position: Int)

    fun onClickLikeButton(position: Int, idPost: Int)
}