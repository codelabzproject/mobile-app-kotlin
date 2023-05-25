package com.example.mobile_app_kotlin.view

import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R

class CardPostFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_postagem, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cardPostTimeline = view.findViewById<LinearLayout>(R.id.card_post_timeline)

        cardPostTimeline.setOnClickListener {
            findNavController().navigate(R.id.action_timelineFragment_to_postExpandedFragment)
        }
    }

    // Método para lidar com o clique no botão de curtir
    fun onLikeButtonClick(position: Int) {
        // Lógica para tratar o clique no botão de curtir
        // ...
    }

    // Método para lidar com o clique no botão de descurtir
    fun onDislikeButtonClick(position: Int) {
        // Lógica para tratar o clique no botão de descurtir
        // ...
    }

    // Método para atualizar o texto do título do cartão
    fun updateTitleText(newText: String) {
        // Lógica para atualizar o texto do título do cartão
        // ...
    }
}
