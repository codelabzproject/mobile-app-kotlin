package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.net.toUri
import androidx.lifecycle.ViewModelProvider
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
//    private var taskFilter = 0

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

//        taskFilter = requireArguments().getInt(CodeConstants.BUNDLE.CODEFILTER, 0)

        val listener = object : CodeListener {

            override fun onListClick(id: Int) {
                val selectedPost = postViewModel.getPostById(id)
                selectedPost?.let {
                    // Navegar para o PostExpandedFragment
                    findNavController().navigate(R.id.action_timelineFragment_to_postExpandedFragment)
                }
            }

//            override fun onListClick(id: Int) {
//                val selectedPost = postViewModel.getPostById(id)
//                selectedPost?.let {
//                    val bundle = Bundle()
//                    bundle.putParcelable("selectedPost", selectedPost)
//                    val fragment = PostExpandedFragment()
//                    fragment.arguments = bundle
//
//                    // Navegar para o PostExpandedFragment
//                    val transaction = requireActivity().supportFragmentManager.beginTransaction()
//                    transaction.replace(R.id.fragment_container, fragment)
//                    transaction.addToBackStack(null)
//                    transaction.commit()
//                }
//            }


//            override fun onListClick(id: Int) {
////                val intent = Intent(context, TaskFormActivity::class.java)
//                val bundle = Bundle()
//                bundle.putInt(CodeConstants.BUNDLE.TASKID, id)
////                intent.putExtras(bundle)
////                startActivity(intent)
//            }

            override fun onDeleteClick(id: Int) {
//                viewModel.delete(id)
            }

            override fun onLikePost(id: Int) {
//                viewModel.status(id, true)
            }

            override fun onDislikePost(id: Int) {
//                viewModel.status(id, false)
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

        val profileCard = view.findViewById<LinearLayout>(R.id.profile_home_resumed)

        // Adiciona um listener para o evento de clique no botão
        fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_createPostActivity)

            // Faça o que desejar ao clicar no botão
//            Toast.makeText(context, "Botão clicado", Toast.LENGTH_SHORT).show()
        }

        profileCard.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_userProfileFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        postViewModel.getPosts()
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    private fun observe() {
        loginViewModel.name.observe(viewLifecycleOwner) {
//            view?.findViewById<TextView>(R.id.username_user)?.text ?: it
            binding.usernameUser.text = it
            binding.avatarUser.setImageURI("https://raw.githubusercontent.com/codelabzproject/public/main/img/avatar1.svg".toUri())

        }

        postViewModel.posts.observe(viewLifecycleOwner) {
            adapter.updatePosts(it)
        }

//        viewModel.delete.observe(viewLifecycleOwner) {
//            if (!it.status()) {
//                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
//            }
//        }

//        viewModel.status.observe(viewLifecycleOwner) {
//            if (!it.status()) {
//                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
//            }
//        }
    }
}