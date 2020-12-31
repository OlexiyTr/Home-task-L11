package com.example.classwork11

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.classwork11.databinding.ActivityNotificationFromReplyActionBinding
import com.example.classwork11.databinding.ActivityNotificationsBinding

class NotificationFromReplyActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification_from_reply_action)
        setupBinding()
        setupListeners()
        showMessageFromNotification()


    }

    private lateinit var binding : ActivityNotificationFromReplyActionBinding

    private fun setupBinding() {
        binding = ActivityNotificationFromReplyActionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners(){
        binding.btnNotificationActivity.setOnClickListener{
            returnToNotificationActivity()
        }
    }

    private fun returnToNotificationActivity(){
        val intent = Intent(this, NotificationsActivity::class.java)
        startActivity(intent)
    }

    private fun showMessageFromNotification(){
        val textView = findViewById<TextView>(R.id.textViewReplyDirect)

        val remoteReply = RemoteInput.getResultsFromIntent(intent)

        val message = remoteReply.getCharSequence(NotificationsActivity.TXT_REPLY).toString()
        textView.text = message

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NotificationsActivity.NOTIFICATION_ID)
    }


}