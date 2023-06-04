package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentSelectAvatarBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.AvatarListener
import com.example.mobile_app_kotlin.service.model.request.AvatarUserRequest
import com.example.mobile_app_kotlin.service.model.response.AvatarModel
import com.example.mobile_app_kotlin.view.adapter.AvatarAdapter
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.RegisterViewModel
import com.example.mobile_app_kotlin.viewmodel.UserViewModel

class SelectAvatarFragment : Fragment() {

    private var selectedAvatarIndex: Int = -1

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var registerViewModel: RegisterViewModel
    private lateinit var userViewModel: UserViewModel

    private var isCreatePage: Boolean = false

    private var _binding: FragmentSelectAvatarBinding? = null
    private val binding get() = _binding!!

    private val adapter = AvatarAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        if (bundle != null) {
            isCreatePage = bundle.getBoolean(CodeConstants.AUX.isCreatePage)
        }

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        _binding = FragmentSelectAvatarBinding.inflate(inflater, container, false)

        binding.avatarRecyclerView.layoutManager = GridLayoutManager(context, 5)
        binding.avatarRecyclerView.adapter = adapter

        val listener = object : AvatarListener {
            override fun onClickAvatar(position: Int) {
                selectedAvatarIndex = position
            }
        }
        adapter.attachListener(listener)
//        loginViewModel.loadUserName()

        observe()

        binding.textWelcomeAvatar.text = if (isCreatePage) {
            getString(R.string.select_avatar_first_time)
        } else {
            getString(R.string.update_your_avatar)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonConfirmAvatar.setOnClickListener {
            if (selectedAvatarIndex != -1) {
                val selectedAvatar = adapter.getItem(selectedAvatarIndex)
                val avatatRequest = AvatarUserRequest(
                    selectedAvatar.id.toString(),
                    selectedAvatar.iconPng.toString()
                )
                userViewModel.securityPreferences.store(
                    CodeConstants.SHARED.USER_AVATAR, selectedAvatar.iconPng.toString()
                )
                registerViewModel.updateAvatarUser(
                    registerViewModel.loadUserIdCreated(),
                    avatatRequest
                )

                userViewModel.getUser(registerViewModel.loadUserIdCreated())

                if (isCreatePage) {
                    Toast.makeText(
                        context, "Seu avatar foi salvo com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().navigate(R.id.action_selectAvatarFragment_to_timelineActivity)
                } else {
                    Toast.makeText(
                        context,
                        "Seu avatar foi atualizado com sucesso",
                        Toast.LENGTH_SHORT
                    ).show()
                    findNavController().popBackStack()
                }


            }
        }
    }

    private fun observe() {

        val listTypesPost = mutableListOf<AvatarModel>()

        for (i in 1..10) {
            val avatarUrl =
                CodeConstants.AVATAR::class.java.getDeclaredField("avatar$i").get(null) as String
            val avatarModel = AvatarModel(
                id = i,
                iconDrawable = i,
                iconPng = avatarUrl
            )
            listTypesPost.add(avatarModel)
        }


        adapter.updateAvatares(listTypesPost)
        loginViewModel.name.observe(viewLifecycleOwner) { name ->
//            binding.usernameUser.text = name

//            Picasso.get()
//                .load(loginViewModel.loadAvatarPng())
//                .into(binding.avatarUser)
        }

//        postViewModel.listPosts.observe(viewLifecycleOwner) { posts ->
//            adapter.updatePosts(posts)
//        }
    }

}