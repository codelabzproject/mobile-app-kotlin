package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentPostTimelineBinding
import com.example.mobile_app_kotlin.view.adapter.PostAdapter
import com.example.mobile_app_kotlin.viewmodel.PostViewModel

class CardPostFragment(
    private val viewModel: PostViewModel,
    private val adapter: PostAdapter? = null,
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_post_timeline, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    fun onLikeButtonClick(idPost: Int, idUser: Int) {
        viewModel.setLikePost(idPost, idUser)
    }

    fun onDislikeButtonClick(position: Int) {
    }


    fun updateTitleText(newText: String) {
    }
}
