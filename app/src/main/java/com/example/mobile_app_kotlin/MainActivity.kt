package com.example.mobile_app_kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import io.flutter.embedding.android.FlutterActivity;

import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor

private const val FLUTTER_ENGINE_ID = "module_flutter_engine"

class MainActivity : AppCompatActivity() {
    lateinit var flutterEngine: FlutterEngine

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        flutterEngine = FlutterEngine(this)

        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        FlutterEngineCache.getInstance()
            .put(FLUTTER_ENGINE_ID, flutterEngine)

        val myButton= findViewById<Button>(R.id.my_button)
//        myButton.setOnClickListener {
//            startActivity(FlutterActivity.withCachedEngine(FLUTTER_ENGINE_ID).build(this))
//        }
        myButton.setOnClickListener{
            startActivity(
                FlutterActivity.createDefaultIntent(this)
            )
        }
    }


}