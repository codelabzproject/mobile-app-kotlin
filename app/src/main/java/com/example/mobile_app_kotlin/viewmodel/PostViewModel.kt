package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.ValidationModel
import com.example.mobile_app_kotlin.service.repository.PostRepository

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val postRepository = PostRepository(application.applicationContext)
    private var taskFilter = 0

    private val _posts = MutableLiveData<List<PostModel>>()
    val posts: LiveData<List<PostModel>> = _posts

    private val _post = MutableLiveData<PostModel>()
    val post: LiveData<PostModel> = _post

    private val _newCountPost = MutableLiveData<ValidationModel>()
    val newCountPost: LiveData<ValidationModel> = _newCountPost

    private val selectedPost: MutableLiveData<PostModel> = MutableLiveData()

    fun getPosts() {
        postRepository.getPosts(object : APIListener<List<PostModel>> {
            override fun onSuccess(result: List<PostModel>) {
                _posts.value = result
            }

            override fun onFailure(message: String) {}
        }
        )

    }

    fun getPostById(idPost: Int) {
        postRepository.getPostById(idPost, object : APIListener<PostModel> {
            override fun onSuccess(result: PostModel) {

                _post.value = result
            }

            override fun onFailure(message: String) {}
        }
        )

    }

    fun createPost() {
        postRepository.createPost(object : APIListener<PostModel> {
            override fun onSuccess(result: PostModel) {
            }

            override fun onFailure(message: String) {}
        }
        )

    }

    fun setLikePost(idPost: Int, idUser: Int) {
        postRepository.setLikePost(idPost, idUser, object : APIListener<Void> {
            override fun onSuccess(result: Void) {

                _newCountPost.value = ValidationModel()
            }

            override fun onFailure(message: String) {
                _newCountPost.value = ValidationModel(message)
            }
        }
        )
    }

    fun setDislikePost(idPost: Int, idUser: Int) {
        postRepository.setDislikePost(idPost, idUser, object : APIListener<String> {
            override fun onSuccess(result: String) {
                _newCountPost.value = ValidationModel()
            }

            override fun onFailure(message: String) {
            }
        }
        )
    }




    fun setSelectedPost(post: PostModel) {
        selectedPost.value = post
    }

    fun getSelectedPost(): LiveData<PostModel> {
        return selectedPost
    }

    // Restante do código do ViewModel

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

