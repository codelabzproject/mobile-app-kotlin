package com.example.mobile_app_kotlin.view

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import android.widget.AdapterView.OnItemSelectedListener

class FormCreatePostFragment : Fragment() {

    private lateinit var spinnerOptions: Spinner

    lateinit var spinner: Spinner
    var selectedTopic:Int = 0

    val topicsMap = mapOf<Int, String>(
        1 to "Flutter",
        2 to "React",
        3 to "Java"
    )

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


    }

}