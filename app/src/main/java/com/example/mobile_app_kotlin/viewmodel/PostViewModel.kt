package com.example.mobile_app_kotlin.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.request.CreateCommentRequest
import com.example.mobile_app_kotlin.service.model.request.CreatePostRequest
import com.example.mobile_app_kotlin.service.model.response.CommentModel
import com.example.mobile_app_kotlin.service.model.response.PostExpandedModel
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.RiseModel
import com.example.mobile_app_kotlin.service.repository.PostRepository
import com.example.mobile_app_kotlin.service.repository.SecurityPreferences

class PostViewModel(application: Application) : AndroidViewModel(application) {

    private val postRepository = PostRepository(application.applicationContext)
    val securityPreferences = SecurityPreferences(application.applicationContext)

    private val _listPosts = MutableLiveData<List<PostModel>>()
    val listPosts: LiveData<List<PostModel>> = _listPosts

    private val _postExpanded = MutableLiveData<PostExpandedModel>()
    val postExpanded: LiveData<PostExpandedModel> = _postExpanded

    private val _post = MutableLiveData<PostModel>()
    val post: LiveData<PostModel> = _post

    private val _risePostModel = MutableLiveData<RiseModel>()
    var risePostModel: LiveData<RiseModel> = _risePostModel

    private val _riseCommentModel = MutableLiveData<RiseModel>()
    val riseCommentModel: LiveData<RiseModel> = _riseCommentModel

    private val _commentModel = MutableLiveData<CommentModel>()
    val commentModel: LiveData<CommentModel> = _commentModel

    private val selectedPost: MutableLiveData<PostModel> = MutableLiveData()

    fun getPosts(idUser: Int) {
        postRepository.getPosts(idUser, object : APIListener<List<PostModel>> {
            override fun onSuccess(result: List<PostModel>) {
                _listPosts.value = result.asReversed()
            }

            override fun onFailure(message: String) {}
        }
        )

    }

    fun getPostById(idPost: Int) {
        postRepository.getPostById(idPost, object : APIListener<PostExpandedModel> {
            override fun onSuccess(result: PostExpandedModel) {
                _postExpanded.value = result
            }

            override fun onFailure(message: String) {}
        }
        )

    }

    fun getPostsByIdTopic(idTopic: Int, idUser: Int) {
        postRepository.getPostsByIdTopic(idTopic, idUser, object : APIListener<List<PostModel>> {
            override fun onSuccess(result: List<PostModel>) {
                _listPosts.value = result
            }

            override fun onFailure(message: String) {}
        }
        )

    }

    fun createDiscussion(createPostRequest: CreatePostRequest) {
        postRepository.createDiscussion(createPostRequest, object : APIListener<PostModel> {
            override fun onSuccess(result: PostModel) {
                _post.value = result
            }

            override fun onFailure(message: String) {}
        }
        )
    }

    fun createComment(createCommentRequest: CreateCommentRequest) {
        postRepository.createComment(createCommentRequest, object : APIListener<CommentModel> {
            override fun onSuccess(result: CommentModel) {
                _commentModel.value = result
            }

            override fun onFailure(message: String) {}
        }
        )
    }

    fun createDoubt(createPostRequest: CreatePostRequest) {
        postRepository.createDoubt(createPostRequest, object : APIListener<PostModel> {
            override fun onSuccess(result: PostModel) {
                _post.value = result
            }

            override fun onFailure(message: String) {}
        }
        )
    }

    fun setLikePost(idPost: Int, idUser: Int) {
        postRepository.setLikePost(idPost, idUser, object : APIListener<RiseModel> {
            override fun onSuccess(result: RiseModel) {
                _risePostModel.value = result
            }

            override fun onFailure(message: String) {
            }
        }
        )
    }

    fun setLikeComment(idComment: Int, idUser: Int) {
        postRepository.setLikeComment(idComment, idUser, object : APIListener<RiseModel> {
            override fun onSuccess(result: RiseModel) {
                _riseCommentModel.value = result
            }

            override fun onFailure(message: String) {
            }
        }
        )
    }



//    fun setSelectedPost(post: PostModel) {
//        selectedPost.value = post
//    }
//
//    fun getSelectedPost(): LiveData<PostModel> {
//        return selectedPost
//    }

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

