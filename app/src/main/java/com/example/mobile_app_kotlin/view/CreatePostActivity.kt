package com.example.mobile_app_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.service.model.request.CreatePostRequest
import com.example.mobile_app_kotlin.service.model.response.SpinnerItem
import com.example.mobile_app_kotlin.viewmodel.LoginViewModel
import com.example.mobile_app_kotlin.viewmodel.PostViewModel
import com.example.mobile_app_kotlin.viewmodel.TopicViewModel

class CreatePostActivity : AppCompatActivity() {
    private lateinit var selectedTopic: SpinnerItem
    private lateinit var selectedTypePosts: SpinnerItem

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var topicViewModel: TopicViewModel
    private lateinit var postViewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        supportActionBar?.hide()
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        topicViewModel = ViewModelProvider(this).get(TopicViewModel::class.java)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)

        topicViewModel.getTopics()

        val spinnerTopics = findViewById<Spinner>(R.id.spinner_topics)
        val spinnerTypesPosts = findViewById<Spinner>(R.id.spinner_types_posts)

        val listTypesPost = listOf(
            SpinnerItem(0, R.drawable.circulo, "Escolha um tipo de postagem"),
            SpinnerItem(1, R.drawable.discussao_icon, "Discussão"),
            SpinnerItem(2, R.drawable.duvida_icon, "Dúvida"),
        )
        topicViewModel.topics.observe(this) { topics ->
            val listTopics: List<SpinnerItem> =
                listOf(SpinnerItem(0, R.drawable.circulo, "Escolha um tópico"))

            val updatedTopics = listTopics + topics.map { topic ->
                SpinnerItem(topic.idTopic, topic.image?.toIntOrNull() ?: 0, topic.name)
            }

            val adapterTopics = CustomSpinnerAdapter(this, updatedTopics)

            adapterTopics.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

            setupSpinner(spinnerTopics, updatedTopics, adapterTopics) {
                selectedTopic = updatedTopics[it]
            }

            spinnerTopics.adapter = adapterTopics
        }

        val adapterTypePost = CustomSpinnerAdapter(this, listTypesPost)

        setupSpinner(spinnerTypesPosts, listTypesPost, adapterTypePost) {
            selectedTypePosts = listTypesPost[it]
        }

        adapterTypePost.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerTypesPosts.adapter = adapterTypePost

        val createPostButton = findViewById<Button>(R.id.button_create_post)
        createPostButton.setOnClickListener {
            handleForm()
        }
    }

    private fun handleForm() {
        val title = findViewById<EditText>(R.id.title_form_create_post).text.toString()
        val content = findViewById<EditText>(R.id.content_form_create_post).text.toString()

        val createPostRequest = CreatePostRequest(
            title,
            content,
            loginViewModel.loadUserIdLogged(),
            selectedTopic.id,
        )

        if (selectedTypePosts.id != 0) {
            if (selectedTopic.id == 1) {
                postViewModel.createDiscussion(createPostRequest)
            } else {
                postViewModel.createDoubt(createPostRequest)
            }
            finish()
        } else {
            Toast.makeText(
                applicationContext,
                "Selecione qual vai ser o tipo de postagem",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    private fun setupSpinner(
        spinner: Spinner,
        items: List<SpinnerItem>,
        adapter: CustomSpinnerAdapter,
        onItemSelected: (position: Int) -> Unit
    ) {
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                onItemSelected(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }
}