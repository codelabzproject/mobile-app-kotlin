package com.example.mobile_app_kotlin.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonRegisterPage = view.findViewById<TextView>(R.id.button_register_page)
        val buttonForgetPasswordPage = view.findViewById<TextView>(R.id.button_forget_password)

        buttonRegisterPage.setOnClickListener {
            goToRegisterPage()
        }

        buttonForgetPasswordPage.setOnClickListener {
            goToForgetPasswordPage()
        }
    }

    private fun goToRegisterPage() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun goToForgetPasswordPage() {
        findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
    }

}