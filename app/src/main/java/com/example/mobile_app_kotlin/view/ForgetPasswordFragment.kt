package com.example.mobile_app_kotlin.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.viewmodel.ForgetPasswordViewModel

class ForgetPasswordFragment : Fragment() {
    private lateinit var viewModel: ForgetPasswordViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(ForgetPasswordViewModel::class.java)

        return inflater.inflate(R.layout.fragment_forget_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val backToLogin = view.findViewById<ImageView>(R.id.arrow_back_button)
        val returnLoginPage = view.findViewById<Button>(R.id.button_forget_password)

        backToLogin.setOnClickListener {
            returnLoginpage()
        }

        returnLoginPage.setOnClickListener {
            val email = view.findViewById<EditText>(R.id.inputForgetPassword).text.toString()
            viewModel.forgetPassword(email)
        }

        observe()

    }

    private fun observe() {
        viewModel.message.observe(viewLifecycleOwner) {
            if (it.status()) {
                Toast.makeText(context, "Nova senha enviada ao e-mail dos usuários", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun returnLoginpage() {
        findNavController().navigate(R.id.action_forgetPasswordFragment_to_loginFragment)
    }

}