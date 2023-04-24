package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class PostModel (
    @SerializedName("idPost") val idPost: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("comments") val comments: Int,
    @SerializedName("owner") val user: UserModel,
    @SerializedName("topic") val topic: TopicModel,
    @SerializedName("points") val points: Int,
    @SerializedName("createdIn") val createdIn: String,
    @SerializedName("doubt") val doubt: Boolean
)
