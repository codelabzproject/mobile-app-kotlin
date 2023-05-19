package com.example.mobile_app_kotlin.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentHomeBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.view.adapter.PostAdapter
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.example.mobile_app_kotlin.viewmodel.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var postViewModel: PostViewModel
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

        val listener = object : CodeListener {
            //            override fun onLikePost(position: Int, id: Int) {
//            }
            override fun onClickPost(position: Int) {
//                val selectedPost = adapter.getItem(position)
//                val bundle = bundleOf("selectedPost" to selectedPost)
//                findNavController().navigate(R.id.action_timelineFragment_to_postExpandedFragment, bundle)
            }
        }

//                val listener = object : TaskListener {
//            override fun onListClick(id: Int) {
//                val intent = Intent(context, TaskFormActivity::class.java)
//                val bundle = Bundle()
//                bundle.putInt(TaskConstants.BUNDLE.TASKID, id)
//                intent.putExtras(bundle)
//                startActivity(intent)
//            }
//
//            override fun onDeleteClick(id: Int) {
//                viewModel.delete(id)
//            }
//
//            override fun onCompleteClick(id: Int) {
//                viewModel.status(id, true)
//            }
//
//            override fun onUndoClick(id: Int) {
//                viewModel.status(id, false)
//            }
//        }

        adapter.attachListener(listener)
        loginViewModel.loadUserName()

        observe()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabButton = view.findViewById<FloatingActionButton>(R.id.button_add_new_post)

        val profileCard = view.findViewById<LinearLayout>(R.id.profile_home_resumed)

        fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_createPostActivity)
        }

        profileCard.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_userProfileFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        postViewModel.getPosts()
    }

    private fun observe() {
        loginViewModel.name.observe(viewLifecycleOwner) {
            binding.usernameUser.text = it
//            binding.avatarUser.setImageURI("https://raw.githubusercontent.com/codelabzproject/public/main/img/avatar1.svg".toUri())
        }

        postViewModel.posts.observe(viewLifecycleOwner) {
            adapter.updatePosts(it)
        }
    }
}