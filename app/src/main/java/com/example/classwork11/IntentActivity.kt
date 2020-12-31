package com.example.classwork11

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.classwork11.databinding.ActivityIntentBinding


class IntentActivity : AppCompatActivity(){

    private lateinit var binding : ActivityIntentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    companion object{
        const val INTENT_LOG_TAG = "INTENT_LOG_TAG"

        fun start(context : Context){
            context.startActivity(Intent(context,IntentActivity::class.java))
        }
    }

    private fun setupBinding(){
        binding = ActivityIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners(){
        binding.btnOpenMap.setOnClickListener{
            Log.d(INTENT_LOG_TAG,"binding.btnOpenMap - click")
            val callIntent = Intent()
            callIntent.action = Intent.ACTION_VIEW
            callIntent.data = Uri.parse("geo:37.7749,-122.4194")
            callIntent.setPackage("com.google.android.apps.maps")
            startActivity(callIntent)
        }

        binding.btnNewActivity.setOnClickListener{
            Log.d(INTENT_LOG_TAG,"binding.btnNewActivity - click")
            openNewACtivity()
        }
    }
    private fun openNewACtivity() {
        val editTextString = findViewById<EditText>(R.id.editTextString)
        val message = editTextString.text.toString()

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val number = Integer.parseInt(editTextNumber.text.toString())

        NewActivityFromIntentsActivity.start(this, message,number)
    }

}