package com.example.classwork11

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.classwork11.databinding.ActivityNewFromIntentActivityBinding


class NewActivityFromIntentsActivity : AppCompatActivity() {
    companion object {

        private const val NAME_KEY = "NAME_KEY"
        private const val NUMBER_KEY = "NUMBER_KEY"

        fun start(context: Context, name: String, number: Int) {
            val intent = Intent(context, NewActivityFromIntentsActivity::class.java)
            intent.putExtra(NAME_KEY, name)
            intent.putExtra(NUMBER_KEY,number)
            context.startActivity(intent)
        }
    }

    private lateinit var binding: ActivityNewFromIntentActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupData()
    }

    private fun setupBinding() {
        binding = ActivityNewFromIntentActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupData() {
        val name = intent.getStringExtra(NAME_KEY)
        val number = intent.getIntExtra(NUMBER_KEY,0)

        binding.stringView.text = name
        binding.numberView.text = number.toString()
    }
}