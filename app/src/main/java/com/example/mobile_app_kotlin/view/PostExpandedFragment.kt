package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentPostExpandedBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.CommentListener
import com.example.mobile_app_kotlin.service.model.request.CreateCommentRequest
import com.example.mobile_app_kotlin.view.adapter.CommentAdapter
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.squareup.picasso.Picasso

class PostExpandedFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var postViewModel: PostViewModel
    private lateinit var cardPost: CardPostFragment

    private var _binding: FragmentPostExpandedBinding? = null
    private val binding get() = _binding!!

    private val commentAdapter = CommentAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        val bundle = arguments
        if (bundle != null) {
            postViewModel.getPostById(bundle.getInt("postId"))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        postViewModel = ViewModelProvider(requireActivity()).get(PostViewModel::class.java)
        _binding = FragmentPostExpandedBinding.inflate(inflater, container, false)
        binding.recyclerComments.layoutManager = LinearLayoutManager(context)
        binding.recyclerComments.adapter = commentAdapter

        cardPost = CardPostFragment(postViewModel)

        val listener = object : CommentListener {
            override fun onClickLikeComment(position: Int, idPost: Int) {
                cardPost.onLikeButtonClick(idPost, loginViewModel.loadUserIdLogged())
                postViewModel.getPostById(loginViewModel.loadUserIdLogged())
            }
        }
        commentAdapter.attachListener(listener)

        observe()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val likePostButton = view.findViewById<ImageButton>(R.id.likePostButton)
        val buttonPublicComment = view.findViewById<Button>(R.id.button_public_comment)

        likePostButton.setOnClickListener {
            postViewModel.postExpanded.value?.let { it1 ->
                cardPost.onLikeButtonClick(
                    it1.idPost,
                    postViewModel.securityPreferences.get(CodeConstants.SHARED.USER_ID).toInt(),
                )
            }
        }

        buttonPublicComment.setOnClickListener {
            createComment(view)
        }

//        val fabButton = view.findViewById<FloatingActionButton>(R.id.button_add_new_post)
//        fabButton.setOnClickListener {
//            findNavController().navigate(R.id.action_timelineFragment_to_createPostActivity)
//        }
//
//        val profileCard = view.findViewById<LinearLayout>(R.id.profile_home_resumed)
//        profileCard.setOnClickListener {
//            findNavController().navigate(R.id.action_timelineFragment_to_userProfileFragment)
//        }
//
//        val seeHighTopics = view.findViewById<LinearLayout>(R.id.buttonSeeHighTopics)
//        seeHighTopics.setOnClickListener {
//            findNavController().navigate(R.id.action_timelineFragment_to_topicFragment)
//        }
    }

    private fun createComment(view: View) {
        val comment = view.findViewById<EditText>(R.id.edit_new_comment).text.toString()
        /*
        val content: String,
        val idUser: Int,
        val idPost: Int
        */

        val createComment =
            CreateCommentRequest(
                comment,
                postViewModel.securityPreferences.get(CodeConstants.SHARED.USER_ID).toInt(),
                postViewModel.postExpanded.value?.idPost ?: 0,
            )

        postViewModel.createComment(createComment)
    }


    private fun observe() {
        postViewModel.postExpanded.observe(viewLifecycleOwner) { postExpandedModel ->
            binding.postExpandedFragment.nameTopic.text = postExpandedModel.topic.name

            binding.postExpandedFragment.titlePost.text = postExpandedModel.title
            binding.postExpandedFragment.contentPost.text = postExpandedModel.content

            binding.postExpandedFragment.countLikes.text = if (postExpandedModel.points >= 0) {
                postExpandedModel.points.toString()
            } else {
                "0"
            }

            val userInfoPost =
                context?.getString(
                    R.string.enviado_por_user_em_data,
                    postExpandedModel.user.name,
//                    postExpandedModel.createdIn ?:
                    context?.getString(R.string.algum_dia)
                )
            binding.postExpandedFragment.userInfoPost.text = userInfoPost

            val qtdCommentsPost =
                context?.getString(
                    R.string.qtd_comentarios,
                    postExpandedModel.comments.size.toString(),
                )
            binding.postExpandedFragment.commentsPosts.text = qtdCommentsPost

            binding.postExpandedFragment.nameTopic.text = postExpandedModel.topic.name

            Picasso.get()
                .load(postExpandedModel.topic.image)
                .into(binding.postExpandedFragment.svgTopicPost)

            val imageLike = if (postExpandedModel.userHasVoted) {
                R.drawable.like_up_enable
            } else {
                R.drawable.like_up_disabled
            }
            binding.postExpandedFragment.likePostButton.setImageResource(imageLike)

            commentAdapter.updateComments(postExpandedModel.comments)

        }

        postViewModel.risePostModel.observe(viewLifecycleOwner) { riseModel ->
            binding.postExpandedFragment.countLikes.text = riseModel.postPointTotal.toString()
            val imageLike = if (riseModel.userHasVoted) {
                R.drawable.like_up_enable
            } else {
                R.drawable.like_up_disabled
            }
            binding.postExpandedFragment.likePostButton.setImageResource(imageLike)
        }

        postViewModel.commentModel.observe(viewLifecycleOwner) { commentModel ->
            postViewModel.postExpanded.value?.comments?.add(commentModel)
            postViewModel.postExpanded.value?.comments?.let { commentAdapter.updateComments(it) }

        }
    }
}
