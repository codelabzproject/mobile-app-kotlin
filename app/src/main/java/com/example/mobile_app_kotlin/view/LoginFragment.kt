package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonRegisterPage = view.findViewById<TextView>(R.id.button_register_page)
        val buttonForgetPasswordPage = view.findViewById<TextView>(R.id.button_forget_password)
        val buttonLoginPage = view.findViewById<TextView>(R.id.button_login)

        buttonRegisterPage.setOnClickListener {
            goToRegisterPage()
        }

        buttonForgetPasswordPage.setOnClickListener {
            goToForgetPasswordPage()
        }

        buttonLoginPage.setOnClickListener {
            login(view)
        }

        viewModel.verifyLoggedUser()

        observe()
    }

    private fun goToRegisterPage() {
        findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
    }

    private fun goToForgetPasswordPage() {
        findNavController().navigate(R.id.action_loginFragment_to_forgetPasswordFragment)
    }

    private fun login(view: View) {
        handleLogin(view)
    }

    private fun observe() {
        viewModel.login.observe(this) {
            if (it.status()) {
                findNavController().navigate(R.id.action_loginFragment_to_timelineActivity)
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()

            }
        }

        viewModel.loggedUser.observe(this) {
            if (it) {
                findNavController().navigate(R.id.action_loginFragment_to_timelineActivity)
            }
        }

    }

    private fun handleLogin(view: View) {
        val email = view.findViewById<EditText>(R.id.inputEmailLogin).text.toString()
        val password = view.findViewById<EditText>(R.id.inputSenhaLogin).text.toString()

        viewModel.doLogin(email, password)
    }
}