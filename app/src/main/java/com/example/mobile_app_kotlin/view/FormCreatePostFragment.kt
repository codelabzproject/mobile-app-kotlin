package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.mobile_app_kotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FormCreatePostFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_create_post, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonCreatePost = view.findViewById<FloatingActionButton>(R.id.button_create_post)

        // Adiciona um listener para o evento de clique no botão
        buttonCreatePost.setOnClickListener {

            // Faça o que desejar ao clicar no botão
            Toast.makeText(context, "Botão clicado", Toast.LENGTH_SHORT).show()
        }
    }

}