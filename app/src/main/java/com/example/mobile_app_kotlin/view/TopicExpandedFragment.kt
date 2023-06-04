package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentTopicExpandedBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.PostListener
import com.example.mobile_app_kotlin.view.adapter.PostAdapter
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.example.mobile_app_kotlin.viewmodel.TopicViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class TopicExpandedFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var topicViewModel: TopicViewModel
    private lateinit var postViewModel: PostViewModel

    private lateinit var cardPost: CardPostFragment

    private var _binding: FragmentTopicExpandedBinding? = null
    private val binding get() = _binding!!
    private val postAdapter = PostAdapter()

    override fun onResume() {
        super.onResume()
        val bundle = arguments
        if (bundle != null) {
            topicViewModel.getTopicById(
                bundle.getInt("topicId"),
                loginViewModel.loadUserIdLogged(),
            )

            topicViewModel.securityPreferences.store(
                CodeConstants.TOPIC.TOPIC_ID,
                bundle.getInt("topicId").toString()
            )

            postViewModel.getPostsByIdTopic(
                bundle.getInt("topicId"),
                loginViewModel.loadUserIdLogged(),
            )

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        topicViewModel = ViewModelProvider(this).get(TopicViewModel::class.java)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        _binding = FragmentTopicExpandedBinding.inflate(inflater, container, false)

        binding.recyclerAllPosts.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllPosts.adapter = postAdapter

        cardPost = CardPostFragment(postViewModel, postAdapter)

        val listener = object : PostListener {
            override fun onClickPost(position: Int) {
            }

            override fun onClickLikeButton(position: Int, idPost: Int) {
                postViewModel.risePostModel.removeObservers(viewLifecycleOwner)

                cardPost.onLikeButtonClick(
                    idPost,
                    loginViewModel.loadUserIdLogged()
                )

                postViewModel.risePostModel.observe(viewLifecycleOwner) { riseModel ->
                    val post = postAdapter.getItem(position)
                    post.points = riseModel.postPointTotal
                    post.userHasVoted = riseModel.userHasVoted
                    postAdapter.notifyItemChanged(position)
                }
            }

        }

        postAdapter.attachListener(listener)

        observe()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonFollowTopic = view.findViewById<Button>(R.id.button_follow_topic)
        buttonFollowTopic.setOnClickListener {
            topicViewModel.setFollowTopic(
                topicViewModel.getIdTopicExpanded(),
                loginViewModel.loadUserIdLogged()
            )
        }

    }


    private fun observe() {
        topicViewModel.topicExpanded.observe(viewLifecycleOwner) { topicModel ->

            binding.nameTopic.text = topicModel.name

            Picasso.get()
                .load(topicModel?.image)
                .into(binding.avatarTopic)

            binding.descTopic.text = topicModel.about
            binding.qtdFollowedsTopic.text = topicModel.countFollowers.toString()

            if (topicModel.userHasFollowed) {
                binding.buttonFollowTopic.text = "Seguindo"
            } else {
                binding.buttonFollowTopic.text = "Seguir"
            }
        }

        postViewModel.listPosts.observe(viewLifecycleOwner) { posts ->
            if (posts.isNotEmpty()) {
                postAdapter.updatePosts(posts)
                binding.recyclerAllPosts.visibility = View.VISIBLE
                binding.listPostEmpty.visibility = View.GONE
            } else {
                binding.recyclerAllPosts.visibility = View.GONE
                binding.listPostEmpty.visibility = View.VISIBLE
            }
        }

        topicViewModel.topicFollows.observe(viewLifecycleOwner) {
            binding.qtdFollowedsTopic.text = it.postPointTotal.toString()

            if (it.userHasFollowedTopic) {
                binding.buttonFollowTopic.text = "Seguindo"
            } else {
                binding.buttonFollowTopic.text = "Seguir"
            }
        }
    }
}