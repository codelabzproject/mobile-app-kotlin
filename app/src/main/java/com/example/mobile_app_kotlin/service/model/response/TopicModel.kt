package com.example.mobile_app_kotlin.service.model.response

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Topic")
class TopicModel {

    @SerializedName("idTopic")
    @ColumnInfo(name = "id")
    @PrimaryKey
    var idTopic: Int = 0

    @SerializedName("name")
    @ColumnInfo(name = "name")
    var name: String? = null

    @SerializedName("about")
    @ColumnInfo(name = "about")
    var about: String? = null

//    @SerializedName("createdAt")
//    @ColumnInfo(name = "createdAt")
//    var createdAt: String? = null

    @SerializedName("png")
    @ColumnInfo(name = "png")
    var image: String? = null

    @SerializedName("countFollowers")
    @ColumnInfo(name = "countFollowers")
    var countFollowers: Int = 0

    @SerializedName("userHasFollowed")
    @ColumnInfo(name = "userHasFollowed")
    var userHasFollowed: Boolean = false

}