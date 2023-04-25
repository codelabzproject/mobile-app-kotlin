package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentProfileBinding
import com.example.mobile_app_kotlin.databinding.FragmentUserProfileBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.view.adapter.UserAdapter
import com.example.mobile_app_kotlin.viewmodel.UserViewModel

class ProfileFragment : Fragment() {

        private lateinit var userViewModel: UserViewModel
        private var _binding: FragmentProfileBinding? = null
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
            _binding = FragmentProfileBinding.inflate(inflater, container, false)

        binding.recycleProfile.layoutManager = LinearLayoutManager(context)
        binding.recycleProfile.adapter = adapter

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
            userViewModel.getUser(1)
        }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

        private fun observe() {
            userViewModel.user.observe(viewLifecycleOwner) {
                adapter.updateUser(it)
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