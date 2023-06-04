package com.example.mobile_app_kotlin.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.service.constants.CodeConstants
import com.example.mobile_app_kotlin.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {
    private lateinit var viewModel: RegisterViewModel
    private val DRAWABLE_RIGHT = 2 // declarando a constante como variável global

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Variáveis da classe
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val returnLoginPage = view.findViewById<ImageView>(R.id.arrow_back_button)
        val registerButton = view.findViewById<Button>(R.id.button_register)

        val inputRegisterPassword = view.findViewById<EditText>(R.id.inputRegisterPassword)
        val inputConfirmPassword = view.findViewById<EditText>(R.id.inputRegisterConfirmPassword)

        inputRegisterPassword.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (inputRegisterPassword.right - inputRegisterPassword.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                    if (inputRegisterPassword.transformationMethod is PasswordTransformationMethod) {

                        inputRegisterPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        inputConfirmPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()

                        inputRegisterPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_locked, 0, R.drawable.icon_visibility_on, 0)

                    } else {
                        inputRegisterPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                        inputConfirmPassword.transformationMethod = PasswordTransformationMethod.getInstance()

                        inputRegisterPassword.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_locked, 0, R.drawable.icon_visibility_off, 0)
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }

        returnLoginPage.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        registerButton.setOnClickListener {
            handleSave(view)
        }

        observe()
    }

    private fun observe() {
        viewModel.user.observe(viewLifecycleOwner) {
            if (it.status()) {
                val bundle = Bundle()
                bundle.putBoolean(CodeConstants.AUX.isCreatePage, true)
                findNavController().navigate(R.id.action_registerFragment_to_selectAvatarFragment, bundle)
                Toast.makeText(context, getString(R.string.register_sucess), Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun handleSave(view: View) {
        val name = view.findViewById<EditText>(R.id.inputRegisterName).text.toString()
        val email = view.findViewById<EditText>(R.id.inputRegisterEmail).text.toString()
        val password = view.findViewById<EditText>(R.id.inputRegisterPassword).text.toString()
        val nickname = view.findViewById<EditText>(R.id.inputNicknameUser).text.toString()

        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || nickname.isEmpty()) {
            Toast.makeText(context, getString(R.string.insert_all_camps_to_continue), Toast.LENGTH_SHORT).show()
        } else {
            viewModel.create(email, password, name, nickname)
        }

    }
}