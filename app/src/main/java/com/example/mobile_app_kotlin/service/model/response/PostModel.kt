package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class PostModel {

    /*
    {
    "idPost": 0,
    "title": "string",
    "content": "string",
    "comments": 0,
    "owner": {
      "idUser": 0,
      "name": "string",
      "nickname": "string",
      "avatar": "string",
      "about": "string"
    },
    "topic": {
      "idTopic": 0,
      "name": "string",
      "svg": "string"
    },
    "points": 0,
    "createdIn": "2023-04-13T07:28:03.003Z",
    "doubt": true
  }
  */

    @SerializedName("idPost")
    var idPost: Int = 0

    @SerializedName("title")
    var title: String = ""

    @SerializedName("content")
    var content: String = ""

    @SerializedName("comments")
    var comments: Int = 0

    @SerializedName("points")
    var points: Int = 0

    @SerializedName("createdIn")
    var createdIn: String = ""

    @SerializedName("doubt")
    var doubt: Boolean = true

}