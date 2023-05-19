package com.example.mobile_app_kotlin.view

import android.graphics.drawable.PictureDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentUserProfileBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.view.adapter.UserAdapter
import com.example.mobile_app_kotlin.viewmodel.UserViewModel
import com.squareup.picasso.Picasso

class UserProfileFragment : Fragment() {
    private lateinit var userViewModel: UserViewModel
    private var _binding: FragmentUserProfileBinding? = null
    private val binding get() = _binding!!
    private val adapter = UserAdapter()
//    private var taskFilter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        _binding = FragmentUserProfileBinding.inflate(inflater, container, false)

//        binding.recyclerAllPosts.layoutManager = LinearLayoutManager(context)
//        binding.recyclerAllPosts.adapter = adapter

//        taskFilter = requireArguments().getInt(CodeConstants.BUNDLE.CODEFILTER, 0)

        val listener = object : CodeListener {
            override fun onListClick(id: Int) {
//                val intent = Intent(context, TaskFormActivity::class.java)
                val bundle = Bundle()
                bundle.putInt(CodeConstants.BUNDLE.TASKID, id)
//                intent.putExtras(bundle)
//                startActivity(intent)
            }

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

//        userViewModel.loadUserName()

        observe()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        userViewModel.getUser(
            userViewModel.securityPreferences.get(CodeConstants.SHARED.USER_ID).toInt()
        )

    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    private fun observe() {
        userViewModel.user.observe(viewLifecycleOwner) { user ->
            // Atualizar o TextView com o nome do perfil
            adapter.updateUser(user)
            binding.nameProfilePage.text = user.name
            binding.bioProfilePage.text = if (user.about.isNullOrEmpty()) {
                "Sem biografia"
            } else {
                user.about
            }
            binding.usernameProfilePage.text = user.nickname

            // Carregar a imagem do perfil utilizando o Picasso
            Picasso.get()
                .load("https://raw.githubusercontent.com/codelabzproject/public/main/img/avatar1.svg")
                .into(binding.avatarUser)
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