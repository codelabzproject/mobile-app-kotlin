package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.view.adapter.PostAdapter
import com.example.mobile_app_kotlin.viewmodel.PostViewModel

class CardPostFragment(
    private val adapter: PostAdapter,
    private val viewModel: PostViewModel,
) :
    Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_card_postagem, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardPostTimeline = view.findViewById<LinearLayout>(R.id.card_post_timeline)

        cardPostTimeline.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_postExpandedFragment)
        }
    }

    fun onClickPost(position: Int) {
    }

    fun onLikeButtonClick(position: Int, idUser: Int) {
        val selectedPost = adapter.getItem(position)
        selectedPost.idPost
        viewModel.setLikePost(
            selectedPost.idPost,
            idUser,
        )
    }


    fun onDislikeButtonClick(position: Int) {
    }


    fun updateTitleText(newText: String) {
    }
}
