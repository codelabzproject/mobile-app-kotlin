package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class TopicFollowModel (
    @SerializedName("followedsTotal")
    val postPointTotal: Int,

    @SerializedName("userHasFollowedTopic")
    var userHasFollowedTopic: Boolean,
    )