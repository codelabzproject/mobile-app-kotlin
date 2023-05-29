package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class AnswerModel(
    @SerializedName("idAnswer")
    val idAnswer: Int,

    @SerializedName("solvedIn")
    val solvedIn: String,

    @SerializedName("solved")
    val solved: Boolean
)
