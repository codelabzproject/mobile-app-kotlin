package com.example.mobile_app_kotlin.service.listener

interface CodeListener {
    fun onClickPost(position: Int)

    fun onClickLikeButton(position: Int)

    fun onClickDislikeButton(position: Int)
}