package com.example.mobile_app_kotlin.service.model

import com.google.gson.annotations.SerializedName

class UserModel {

    @SerializedName("idUser")
    var idUser: Int = 0

    @SerializedName("name")
    var name: String = ""

    @SerializedName("avatar")
    var avatar: String = ""

    @SerializedName("about")
    var about: String = ""

    @SerializedName("disabledIn")
    var disabledIn: String = ""

    @SerializedName("active")
    var active: Boolean = true
}