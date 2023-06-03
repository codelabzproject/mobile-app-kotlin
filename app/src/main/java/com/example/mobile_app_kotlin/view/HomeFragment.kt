package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentHomeBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.PostListener
import com.example.mobile_app_kotlin.view.adapter.PostAdapter
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.example.mobile_app_kotlin.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.squareup.picasso.Picasso

class HomeFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var postViewModel: PostViewModel

    private lateinit var cardPost: CardPostFragment

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = PostAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerAllPosts.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllPosts.adapter = adapter

        cardPost = CardPostFragment(postViewModel, adapter)

        val listener = object : PostListener {

            override fun onClickPost(position: Int) {
                val bundle = Bundle()
                bundle.putInt("postId", adapter.getItem(position).idPost)
                findNavController().navigate(
                    R.id.action_timelineFragment_to_postExpandedFragment,
                    bundle
                )
            }

            override fun onClickLikeButton(position: Int, idPost: Int) {
                cardPost.onLikeButtonClick(
                    idPost,
                    loginViewModel.loadUserIdLogged()
                )
                postViewModel.getPosts(loginViewModel.loadUserIdLogged())

//                postViewModel.riseModel.observe(viewLifecycleOwner) { riseModel ->
//                    val post = adapter.getItem(position)
//                    post.points = riseModel.postPointTotal
//                    post.userHasVoted = riseModel.userHasVoted
//                    adapter.notifyItemChanged(position)
//                    postViewModel.riseModel.removeObservers(viewLifecycleOwner)
//                }
            }
        }

        adapter.attachListener(listener)
        loginViewModel.loadUserName()

        observe()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val fabButton = view.findViewById<FloatingActionButton>(R.id.button_add_new_post)
        fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_createPostActivity)
        }

        val profileCard = view.findViewById<LinearLayout>(R.id.profile_home_resumed)
        profileCard.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_userProfileFragment)
        }

        val seeHighTopics = view.findViewById<LinearLayout>(R.id.buttonSeeHighTopics)
        seeHighTopics.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_topicFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        postViewModel.getPosts(loginViewModel.loadUserIdLogged())
    }

    private fun observe() {
        loginViewModel.name.observe(viewLifecycleOwner) { name ->
            binding.usernameUser.text = name

            Picasso.get()
                .load(loginViewModel.loadAvatarPng())
                .into(binding.avatarUser)
        }

        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            adapter.updatePosts(posts)
        }
    }

}