package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.model.response.TopicModel
import retrofit2.Call
import retrofit2.http.GET

interface TopicService {

    @GET("topics")
    fun getTopics(): Call<List<TopicModel>>

}