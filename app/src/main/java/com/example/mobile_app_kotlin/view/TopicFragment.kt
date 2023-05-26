package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentHomeBinding
import com.example.mobile_app_kotlin.databinding.FragmentTopicBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.TopicListener
import com.example.mobile_app_kotlin.view.adapter.PostAdapter
import com.example.mobile_app_kotlin.view.adapter.TopicAdapter
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.example.mobile_app_kotlin.viewmodel.TopicViewModel
import com.squareup.picasso.Picasso

class TopicFragment : Fragment() {

    private lateinit var topicViewModel: TopicViewModel

    private var _binding: FragmentTopicBinding? = null
    private val binding get() = _binding!!

    private val adapterAllTopics = TopicAdapter()
    private val adapterTopicsByUser = TopicAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onResume() {
        super.onResume()
        topicViewModel.getTopics()
        topicViewModel.getTopicsByUser(topicViewModel.securityPreferences.get(CodeConstants.SHARED.USER_ID).toInt())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        topicViewModel = ViewModelProvider(this).get(TopicViewModel::class.java)

        _binding = FragmentTopicBinding.inflate(inflater, container, false)

        binding.recyclerTopicsFolloweds.layoutManager = LinearLayoutManager(context)
        binding.recyclerTopicsFolloweds.adapter = adapterTopicsByUser

        binding.recyclerAllTopics.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllTopics.adapter = adapterAllTopics

        val listener = object : TopicListener {
            override fun onClickTopic(position: Int) {
                TODO("Not yet implemented")
            }
        }

        adapterTopicsByUser.attachListener(listener)
        adapterAllTopics.attachListener(listener)

        observe()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun observe() {
        topicViewModel.topics.observe(viewLifecycleOwner) {
            adapterAllTopics.updateTopics(it)
        }

        topicViewModel.topicsByUser.observe(viewLifecycleOwner) {
            adapterTopicsByUser.updateTopics(it)
        }
    }
}