package com.example.mobile_app_kotlin.service.repository

import android.content.Context
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.TopicModel
import com.example.mobile_app_kotlin.service.repository.local.CodeDatabase
import com.example.mobile_app_kotlin.service.repository.remote.RetrofitClient
import com.example.mobile_app_kotlin.service.repository.remote.TopicService

class TopicRepository(context: Context) : BaseRepository(context) {

    private val remote = RetrofitClient.getService(TopicService::class.java)

    private val database = CodeDatabase.getDatabase(context).topicDAO()

    fun getTopics(listener: APIListener<List<TopicModel>>) {

//        if (!isConnectionAvailable()) {
//            listener.onFailure(context.getString(R.string.ERROR_INTERNET_CONNECTION))
//            return
//        }

        executeCall(remote.getTopics(), listener)
    }

    fun saveTopics(listTopics: List<TopicModel>) {
        database.clear()
        database.saveList(listTopics)

    }

}