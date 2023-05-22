package com.example.mobile_app_kotlin.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class FormCreatePostFragment : Fragment() {

    private lateinit var spinnerOptions: Spinner

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

        buttonCreatePost.setOnClickListener {

            // Faça o que desejar ao clicar no botão
            Toast.makeText(context, "Botão clicado", Toast.LENGTH_SHORT).show()
        }

        val linearLayout = view.findViewById<LinearLayout>(R.id.topicsSelection)
        linearLayout.setOnClickListener {
            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.topic_item_layout)
            findNavController().navigate(R.id.action_forgetPasswordFragment_to_loginFragment)
            // Configurar a lista de opções clicáveis no modal
            val recyclerView = dialog.findViewById<RecyclerView>(R.id.recycler_all_posts)
//            val adapter = YourAdapter(optionsList) // Substitua YourAdapter e optionsList com os seus valores reais
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = LinearLayoutManager(context)

            dialog.show()
        }

    }

}