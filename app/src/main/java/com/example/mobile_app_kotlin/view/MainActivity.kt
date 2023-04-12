package com.example.mobile_app_kotlin.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobile_app_kotlin.R

//import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}