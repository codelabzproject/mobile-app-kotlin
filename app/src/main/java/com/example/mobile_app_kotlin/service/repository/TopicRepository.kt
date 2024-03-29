package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.TopicFollowModel
import com.example.mobile_app_kotlin.service.model.response.TopicModel
import com.example.mobile_app_kotlin.service.repository.local.CodeDatabase
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient
import com.example.mobile_app_kotlin.service.repository.remote.TopicService

class TopicRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(TopicService::class.java, context)

    private val database = CodeDatabase.getDatabase(context).topicDAO()

    fun getTopics(listener: APIListener<List<TopicModel>>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.getTopics(), listener)
    }

    fun getTopicsByUser(idUser: Int, listener: APIListener<List<TopicModel>>) {
        executeCall(remote.getTopicsByUser(idUser), listener)
    }

    fun getTopicById(idTopic: Int, idUser: Int, listener: APIListener<TopicModel>) {
        executeCall(remote.getTopicById(idTopic, idUser), listener)
    }

    fun setFollowTopic(idTopic: Int, idUser: Int, listener: APIListener<TopicFollowModel>) {
        executeCall(remote.setFollowTopic(idTopic, idUser), listener)
    }


    fun saveTopics(listTopics: List<TopicModel>) {
        database.clear()
        database.saveList(listTopics)

    }

}