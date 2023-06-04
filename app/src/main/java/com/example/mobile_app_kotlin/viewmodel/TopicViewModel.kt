package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.TopicFollowModel
import com.example.mobile_app_kotlin.service.model.response.TopicModel
import com.example.mobile_app_kotlin.service.repository.SecurityPreferences
import com.example.mobile_app_kotlin.service.repository.TopicRepository

class TopicViewModel(application: Application) : AndroidViewModel(application) {

    private val topicRepository = TopicRepository(application.applicationContext)

    val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _topics = MutableLiveData<List<TopicModel>>()
    val topics: LiveData<List<TopicModel>> = _topics

    private val _topicsByUser = MutableLiveData<List<TopicModel>>()
    val topicsByUser: LiveData<List<TopicModel>> = _topicsByUser

    private val _topicExpanded = MutableLiveData<TopicModel>()
    val topicExpanded: LiveData<TopicModel> = _topicExpanded

    private val _topicFollows = MutableLiveData<TopicFollowModel>()
    val topicFollows: LiveData<TopicFollowModel> = _topicFollows
    fun getTopics() {
        topicRepository.getTopics(object : APIListener<List<TopicModel>> {
            override fun onSuccess(result: List<TopicModel>) {
                _topics.value = result
            }

            override fun onFailure(message: String) {}
        })
    }

    fun getTopicsByUser(idUser: Int) {
        topicRepository.getTopicsByUser(idUser, object : APIListener<List<TopicModel>> {
            override fun onSuccess(result: List<TopicModel>) {
                _topicsByUser.value = result
            }

            override fun onFailure(message: String) {}
        })
    }

    fun getTopicById(idTopic: Int, idUser: Int) {
        topicRepository.getTopicById(idTopic, idUser, object : APIListener<TopicModel> {
            override fun onSuccess(result: TopicModel) {
                _topicExpanded.value = result
            }

            override fun onFailure(message: String) {}
        })
    }

    fun setFollowTopic(idTopic: Int, idUser: Int) {
        topicRepository.setFollowTopic(idTopic, idUser, object : APIListener<TopicFollowModel> {
            override fun onSuccess(result: TopicFollowModel) {
                _topicFollows.value = result
            }

            override fun onFailure(message: String) {}
        })
    }

    fun getIdTopicExpanded(): Int {
        return securityPreferences.get(CodeConstants.TOPIC.TOPIC_ID).toInt()
    }

}