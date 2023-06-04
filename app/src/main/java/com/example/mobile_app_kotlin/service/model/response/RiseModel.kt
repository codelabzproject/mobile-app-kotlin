package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class RiseModel(
    @SerializedName("postPointTotal")
    val postPointTotal: Int,

    @SerializedName("userHasVoted")
    var userHasVoted: Boolean,
)