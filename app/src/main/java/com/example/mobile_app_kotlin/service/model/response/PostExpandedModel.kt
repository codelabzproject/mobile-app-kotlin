package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class PostExpandedModel(
    @SerializedName("idPost")
    val idPost: Int,

    @SerializedName("title")
    val title: String,

    @SerializedName("content")
    val content: String,

    @SerializedName("owner")
    val user: UserModel,

    @SerializedName("topic")
    val topic: TopicModel,

    @SerializedName("comments")
    val comments: List<CommentModel>,

    @SerializedName("answer")
    val answer: AnswerModel,

    @SerializedName("points")
    val points: Int,

    @SerializedName("userHasVoted")
    val userHasVoted: Boolean,

//    @SerializedName("createdIn")
//    val createdIn: String,

    @SerializedName("doubt")
    val doubt: Boolean
)