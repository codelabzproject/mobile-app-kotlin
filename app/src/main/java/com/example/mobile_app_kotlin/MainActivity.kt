package com.example.mobile_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
//import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()
    }
}