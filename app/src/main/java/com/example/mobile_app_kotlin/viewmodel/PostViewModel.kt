package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.ValidationModel
import com.example.mobile_app_kotlin.service.repository.PostRepository

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val postRepository = PostRepository(application.applicationContext)
    private var taskFilter = 0

    private val _posts = MutableLiveData<List<PostModel>>()
    val posts: LiveData<List<PostModel>> = _posts

    private val _delete = MutableLiveData<ValidationModel>()
    val delete: LiveData<ValidationModel> = _delete

    private val _status = MutableLiveData<ValidationModel>()
    val status: LiveData<ValidationModel> = _status

    fun getPosts() {
//        taskFilter = filter

        postRepository.getPosts(object : APIListener<List<PostModel>> {
            override fun onSuccess(result: List<PostModel>) {
//                result.forEach {
//                    it.content = postRepository.getPosts(it.content)
//                }
                _posts.value = result
            }

            override fun onFailure(message: String) {}
        }
        )

    }

//        val listener = object : APIListener<List<PostModel>> {
//            override fun onSuccess(result: List<PostModel>) {
//                result.forEach {
//                    it.content = postRepository.getPosts(it.content)
//                }
//                _posts.value = result
}
//            override fun onFailure(message: String) {}


//        when (filter) {
//            CodeConstants.FILTER.ALL -> postRepository.getPosts(listener)
//            CodeConstants.FILTER.NEXT -> postRepository.listNext(listener)
//            else -> postRepository.listOverdue(listener)
//        }

//
//    fun delete(id: Int) {
//        postRepository.delete(id, object : APIListener<Boolean> {
//            override fun onSuccess(result: Boolean) {
//                list(taskFilter)
//            }
//
//            override fun onFailure(message: String) {
//                _delete.value = ValidationModel(message)
//            }
//        })
//    }
//
//    fun status(id: Int, complete: Boolean) {
//        val listener = object : APIListener<Boolean> {
//            override fun onSuccess(result: Boolean) {
//                list(taskFilter)
//            }
//
//            override fun onFailure(message: String) {
//                _status.value = ValidationModel(message)
//            }
//        }
//
//        if (complete) {
//            postRepository.complete(id, listener)
//        } else {
//            postRepository.undo(id, listener)
//        }
//    }

