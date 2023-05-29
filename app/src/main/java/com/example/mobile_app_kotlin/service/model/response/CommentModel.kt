package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class CommentModel(
    @SerializedName("idComment")
    val idComment: Int,

    @SerializedName("idPost")
    val idPost: Int,

    @SerializedName("owner")
    val user: UserModel,

    @SerializedName("content")
    val content: String,

    @SerializedName("likes")
    val likes: Int,

//    @SerializedName("createdIn")
//    val createdIn: String
)