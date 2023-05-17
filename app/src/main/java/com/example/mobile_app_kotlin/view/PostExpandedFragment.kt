package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.viewmodel.PostViewModel

class PostExpandedFragment : Fragment() {
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        postViewModel = ViewModelProvider(requireActivity()).get(PostViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflar o layout do fragmento e inicializar as visualizações

        // Acessar os dados do post selecionado do ViewModel
        val selectedPost = postViewModel.getSelectedPost()

        // Usar os dados do post selecionado para atualizar a UI
        // Exemplo: textViewTitle.text = selectedPost.title

        return view
    }
}
