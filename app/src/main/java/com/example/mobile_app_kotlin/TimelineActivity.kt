package com.example.mobile_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class TimelineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)
        supportActionBar?.hide()
    }
}