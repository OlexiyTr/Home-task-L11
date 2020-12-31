package com.example.classwork11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.classwork11.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    companion object {
        private const val MAIN_LOG_TAG = "MAIN_LOG_TAG"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupBinding()
        setupListeners()
    }

    private lateinit var binding: ActivityMainBinding

    private fun setupBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnNotifications.setOnClickListener {
            Log.d(MAIN_LOG_TAG,"binding.btnNotifications - click")
            NotificationsActivity.start(this)
        }

        binding.btnReceiver.setOnClickListener {
            Log.d(MAIN_LOG_TAG,"binding.btnReceiver - click")
            ReceiverActivity.start(this)
        }

        binding.btnIntents.setOnClickListener {
            Log.d(MAIN_LOG_TAG,"binding.btnIntents - click")
            IntentActivity.start(this)
        }

    }
}