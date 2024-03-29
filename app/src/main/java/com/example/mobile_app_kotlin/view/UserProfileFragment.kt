package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentUserProfileBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.PostListener
import com.example.mobile_app_kotlin.view.adapter.PostAdapter
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.example.mobile_app_kotlin.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class UserProfileFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private lateinit var postViewModel: PostViewModel
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private val postAdapter = PostAdapter()

    private lateinit var cardPost: CardPostFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)

        binding.recyclerAllPosts.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllPosts.adapter = postAdapter

        cardPost = CardPostFragment(postViewModel, postAdapter)

        val listener = object : PostListener {
            override fun onClickPost(position: Int) {
                val bundle = Bundle()
                bundle.putInt("postId", postAdapter.getItem(position).idPost)
                findNavController().navigate(
                    R.id.action_userProfileFragment_to_postExpandedFragment,
                    bundle
                )
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

        val buttonConfig = view.findViewById<ImageButton>(R.id.buttonConfigProfile)
        buttonConfig.setOnClickListener {
            findNavController().navigate(R.id.action_userProfileFragment_to_configFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        userViewModel.getUser(
            userViewModel.securityPreferences.get(CodeConstants.SHARED.USER_ID).toInt()
        )

    }

    private fun observe() {
        userViewModel.user.observe(viewLifecycleOwner) { userProfileModel ->
            userProfileModel?.posts?.let { postAdapter.updatePosts(it) }

            binding.nameProfilePage.text = if (userProfileModel.user?.name.isNullOrEmpty()) {
                "Code Labz"
            } else {
                userProfileModel.user?.name
            }

            binding.bioProfilePage.text = if (userProfileModel.user?.about.isNullOrEmpty()) {
                "Sem biografia"
            } else {
                userProfileModel.user?.about
            }

            binding.usernameProfilePage.text = userProfileModel.user?.nickname

            val topicosQuantidade = userProfileModel.followedTopics?.size.toString()
            val textoQuantidade =
                getString(R.string.profile_total_topics, topicosQuantidade)
            binding.topicoQtdProfilePage.text = textoQuantidade


            // Carregar a imagem do perfil utilizando o Picasso
            Picasso.get()
                .load(userProfileModel.user?.avatar)
                .into(binding.avatarUser)

            if (userProfileModel.posts?.isNotEmpty() == true) {
                binding.recyclerAllPosts.visibility = View.VISIBLE
                binding.listPostEmpty.visibility = View.GONE
            }
        }
    }
}