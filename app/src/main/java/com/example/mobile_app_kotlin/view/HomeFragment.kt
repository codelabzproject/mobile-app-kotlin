package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentHomeBinding
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.view.adapter.CodeAdapter
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class HomeFragment : Fragment() {
    private lateinit var viewModel: PostViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val adapter = CodeAdapter()
//    private var taskFilter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.recyclerAllPosts.layoutManager = LinearLayoutManager(context)
        binding.recyclerAllPosts.adapter = adapter

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

        observe()

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fabButton = view.findViewById<FloatingActionButton>(R.id.button_add_new_post)

        // Adiciona um listener para o evento de clique no botão
        fabButton.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_createPostActivity)

            // Faça o que desejar ao clicar no botão
            Toast.makeText(context, "Botão clicado", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPosts()
    }


//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }

    private fun observe() {
        viewModel.posts.observe(viewLifecycleOwner) {
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