package com.example.classwork11

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.classwork11.databinding.ActivityReceiverBinding

@Suppress("DEPRECATION")
class ReceiverActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiverBinding

    companion object {
        const val RECEIVER_LOG_TAG = "RECEIVER LOG TAG"
        const val CONNECTER_LOG_TAG = "CONNECTER_LOG_TAG"
        fun start(context: Context) {
            context.startActivity(Intent(context, ReceiverActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    override fun onResume() {
        super.onResume()
        registerReceiver(RootReceiver(), IntentFilter(RootReceiver.MY_CUSTOM_ACTION))
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(RootReceiver())
    }

    private fun setupBinding(){
        binding = ActivityReceiverBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners(){
        binding.btnReceive.setOnClickListener{
            val intent = Intent()
            intent.putExtra(RootReceiver.MESSAGE_VALUE,"Receive message")
            intent.action = RootReceiver.MY_CUSTOM_ACTION
            Log.d(RECEIVER_LOG_TAG,"${RootReceiver.RECEIVER_NAME} - user tap on button receive")
            sendBroadcast(intent)
        }
        binding.btnChecker.setOnClickListener{
            Log.d(CONNECTER_LOG_TAG,"user tap on button check")
            checkConnection()
        }
    }
    private fun checkConnection(){
        val connectionManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork : NetworkInfo? = connectionManager.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true
        if(isConnected){
            Toast.makeText(this, "Connected", Toast.LENGTH_LONG).show()
        }
        else{
            Toast.makeText(this, "Disconected", Toast.LENGTH_LONG).show()
        }
    }
}