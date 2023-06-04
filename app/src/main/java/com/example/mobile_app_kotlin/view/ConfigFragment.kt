package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentConfigBinding
import com.example.mobile_app_kotlin.service.model.request.UserUpdateRequest
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.example.mobile_app_kotlin.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class ConfigFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private lateinit var userViewModel: UserViewModel
    private lateinit var postViewModel: PostViewModel

    private var _binding: FragmentConfigBinding? = null
    private val binding get() = _binding!!

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

        _binding = FragmentConfigBinding.inflate(inflater, container, false)

        loginViewModel.loadUserName()

        observe()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageButton = view.findViewById<ImageButton>(R.id.avatarUserProfile)
        imageButton.setOnClickListener {
            findNavController().navigate(R.id.action_configFragment_to_selectAvatarFragment2)
        }

        val saveChanges = view.findViewById<Button>(R.id.buttonSaveChanges)
        saveChanges.setOnClickListener {
            val userUpdateRequest = UserUpdateRequest(
                name = binding.editNameProfile.text.toString(),
                nickname = binding.editUsernameProfile.text.toString(),
                about = binding.editBioProfile.text.toString(),
            )
            userViewModel.updateUserProfile(loginViewModel.loadUserIdLogged(), userUpdateRequest)
            findNavController().popBackStack()
        }
//        val fabButton = view.findViewById<FloatingActionButton>(R.id.button_add_new_post)
//        fabButton.setOnClickListener {
//            findNavController().navigate(R.id.action_timelineFragment_to_createPostActivity)
//        }

    }

    override fun onResume() {
        super.onResume()
        userViewModel.getUser(loginViewModel.loadUserIdLogged())
    }

    private fun observe() {
        userViewModel.user.observe(viewLifecycleOwner) { userProfileModel ->
            val name = userProfileModel.user?.name ?: "seu nome"
            val editableName = Editable.Factory.getInstance().newEditable(name)
            binding.editNameProfile.text = editableName

            val bio = userProfileModel.user?.about ?: "Sem biografia"
            val editableBio = Editable.Factory.getInstance().newEditable(bio)
            binding.editBioProfile.text = editableBio

            val nickname = userProfileModel.user?.nickname ?: ""
            val editableNickname = Editable.Factory.getInstance().newEditable(nickname)
            binding.editUsernameProfile.text = editableNickname

            // Carregar a imagem do perfil utilizando o Picasso
            Picasso.get()
                .load(userProfileModel.user?.avatar)
                .into(binding.avatarUserProfile)

        }
    }

}