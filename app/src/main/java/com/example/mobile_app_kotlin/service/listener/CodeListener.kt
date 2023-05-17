package com.example.mobile_app_kotlin.service.listener

interface CodeListener {

    /**
     * Click para edição
     */
    fun onListClick(id: Int)

    /**
     * Remoção
     */
    fun onDeleteClick(id: Int)

    /**
     * Like post
     */
    fun onLikePost(id: Int)

    /**
     * Dislike post
     */
    fun onDislikePost(id: Int)

}