package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class OwnerModel(
    @SerializedName("idUser")
    val idUser: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("nickname")
    val nickname: String,

    @SerializedName("avatar")
    val avatar: String,

    @SerializedName("avatarPNG")
    val avatarPNG: String,

    @SerializedName("about")
    val about: String
)
