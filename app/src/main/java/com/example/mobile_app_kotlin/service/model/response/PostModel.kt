package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class PostModel(
    @SerializedName("idPost")
    val idPost: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("comments")
    val comments: Int,

    @SerializedName("createdIn")
    val createdIn: String?,

    @SerializedName("owner")
    val user: UserModel,

    @SerializedName("topic")
    var topic: TopicModel? = null,

    @SerializedName("points")
    var points: Int = 0,

    @SerializedName("doubt")
    var doubt: Boolean = false,
)