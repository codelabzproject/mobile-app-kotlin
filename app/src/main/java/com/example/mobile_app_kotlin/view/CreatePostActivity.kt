package com.example.mobile_app_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.mobile_app_kotlin.R

class CreatePostActivity : AppCompatActivity() {

    lateinit var spinner: Spinner
    var selectedTopic:Int = 0

    val topicsMap = mapOf<Int, String>(
        1 to "Flutter",
        2 to "React",
        3 to "Java"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_post)
        supportActionBar?.hide()

        spinner = findViewById(R.id.spinnerTopics)

        spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View, position: Int,
                                        id: Long) {
                selectedTopic = topicsMap.filterValues { it == topicsMap.values.toList()[position] }.entries.first().key
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
        )

        // Create an ArrayAdapter using the string array and a default spinner layout
        val ad: ArrayAdapter<String> = ArrayAdapter<String>(
            baseContext,
            android.R.layout.simple_spinner_item,
            topicsMap.values.toTypedArray())

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item)

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spinner.adapter = ad

    }
}