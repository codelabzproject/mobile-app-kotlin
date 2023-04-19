package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R

class ForgetPasswordFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_forget_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backToLogin = view.findViewById<ImageView>(R.id.arrow_back_button)

        backToLogin.setOnClickListener {
            returnLoginpage()
        }
    }

    private fun returnLoginpage() {
        findNavController().navigate(R.id.action_forgetPasswordFragment_to_loginFragment)
    }

}