package com.example.mobile_app_kotlin.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.navigation.fragment.findNavController
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel
    private val DRAWABLE_RIGHT = 2 // declarando a constante como variável global

    private val GITHUB_CLIENT_ID = "a50aecc707614f3"
    private val GITHUB_CLIENT_SECRET = "fa1f1e18da4f10f8ad37f065738d18e3813b5d28"
    private val GITHUB_REDIRECT_URI = "https://github.com/login/device"
    private val GITHUB_SCOPES = "user,repo"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonRegisterPage = view.findViewById<TextView>(R.id.button_register_page)
        val buttonForgetPasswordPage = view.findViewById<TextView>(R.id.button_forget_password)
        val buttonLoginPage = view.findViewById<TextView>(R.id.button_login)
        val inputSenhaLogin = view.findViewById<EditText>(R.id.inputSenhaLogin)

        inputSenhaLogin.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                if (event.rawX >= (inputSenhaLogin.right - inputSenhaLogin.compoundDrawables[DRAWABLE_RIGHT].bounds.width())) {
                    if (inputSenhaLogin.transformationMethod is PasswordTransformationMethod) {
                        inputSenhaLogin.transformationMethod = HideReturnsTransformationMethod.getInstance()
                        inputSenhaLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_locked, 0, R.drawable.icon_visibility_on, 0)
                    } else {
                        inputSenhaLogin.transformationMethod = PasswordTransformationMethod.getInstance()
                        inputSenhaLogin.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_locked, 0, R.drawable.icon_visibility_off, 0)
                    }
                    return@setOnTouchListener true
                }
            }
            false
        }

        buttonRegisterPage.setOnClickListener {
            goToRegisterPage()
        }

        buttonForgetPasswordPage.setOnClickListener {
            goToForgetPasswordPage()
        }

        buttonLoginPage.setOnClickListener {
            login(view)
        }

//        val githubLoginButton = view.findViewById<ImageView>(R.id.button_login_with_github)
//        githubLoginButton.setOnClickListener {
//            iniciarAutenticacaoGitHub()
//        }

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
        viewModel.login.observe(viewLifecycleOwner) {
            if (it.status()) {
                findNavController().navigate(R.id.action_loginFragment_to_timelineActivity)
            } else {
                Toast.makeText(context, it.message(), Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.loggedUser.observe(viewLifecycleOwner) {
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

    private fun iniciarAutenticacaoGitHub() {
        val url = "https://github.com/login/device/"

        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

//        val uri = activity?.intent?.data
//        if (uri != null && uri.toString().startsWith(GITHUB_REDIRECT_URI)) {
//            val code = uri.getQueryParameter("code")
//            if (code != null) {
//                solicitarTokenAcesso(code)
//            } else {
//                // O usuário cancelou a autenticação
//                // Lide com o cancelamento de acordo com sua lógica
//            }
//        }
    }

    private fun solicitarTokenAcesso(code: String) {
        val url = "https://github.com/login/device"

        lifecycleScope.launch {
            val response = withContext(Dispatchers.IO) {
                val client = OkHttpClient()
                val requestBody = FormBody.Builder()
                    .add("client_id", GITHUB_CLIENT_ID)
                    .add("client_secret", GITHUB_CLIENT_SECRET)
                    .add("code", code)
                    .build()

                val request = Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build()

                client.newCall(request).execute()
            }

            val responseBody = response?.body?.string()

            // Extrair o token de acesso do responseBody
            // Lide com o token de acesso de acordo com sua lógica
        }
    }
}
