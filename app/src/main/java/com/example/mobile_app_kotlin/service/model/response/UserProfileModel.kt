package com.example.mobile_app_kotlin.service.model.response

import com.google.gson.annotations.SerializedName

class UserProfileModel {
    @SerializedName("user")
    var user: UserModel? = null

    @SerializedName("posts")
    var posts: List<PostModel>? = null

    @SerializedName("followedTopics")
    var followedTopics: List<TopicModel>? = null

    @SerializedName("mostRatedPost")
    var mostRatedPost: PostModel? = null
}
