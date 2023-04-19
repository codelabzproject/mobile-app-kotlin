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
import com.example.mobile_app_kotlin.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Variáveis da classe
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val returnLoginPage = view.findViewById<ImageView>(R.id.arrow_back_button)
        val registerButton = view.findViewById<Button>(R.id.button_register)

        returnLoginPage.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        registerButton.setOnClickListener {
            handleSave(view)
        }

        observe()
    }

    private fun observe() {
        viewModel.user.observe(this) {
            if (it.status()) {
                findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun handleSave(view: View) {
        val name = view.findViewById<EditText>(R.id.inputRegisterName).text.toString()
        val email = view.findViewById<EditText>(R.id.inputRegisterEmail).text.toString()
        val password = view.findViewById<EditText>(R.id.inputRegisterPassword).text.toString()

        viewModel.create(email, password, name, "lucaaa")
    }
}