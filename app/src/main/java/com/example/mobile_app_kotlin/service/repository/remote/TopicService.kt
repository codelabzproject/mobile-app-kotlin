package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.response.TopicFollowModel
import com.example.mobile_app_kotlin.service.model.response.TopicModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path

interface TopicService {

    @GET("topics")
    fun getTopics(): Call<List<TopicModel>>

    @GET("topics/user/{idUser}")
    fun getTopicsByUser(
        @Path("idUser", encoded = true) idUser: Int,
        ): Call<List<TopicModel>>

    @GET("topics/{idTopic}/{idUser}")
    fun getTopicById(
        @Path("idTopic", encoded = true) idTopic: Int,
        @Path("idUser", encoded = true) idUser: Int,
    ): Call<TopicModel>

    @PUT("topics/mobile/{idTopic}/{idUser}")
    fun setFollowTopic(
        @Path("idTopic", encoded = true) idTopic: Int,
        @Path("idUser", encoded = true) idUser: Int,
    ): Call<TopicFollowModel>
}