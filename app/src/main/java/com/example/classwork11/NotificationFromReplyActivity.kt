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
        showMessageFromNotification()

    }

    private lateinit var binding : ActivityNotificationFromReplyActionBinding

    private fun setupBinding() {
        binding = ActivityNotificationFromReplyActionBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun showMessageFromNotification(){
        val remoteReply = RemoteInput.getResultsFromIntent(intent)
        if(remoteReply != null){
            val message = remoteReply.getCharSequence(NotificationsActivity.TXT_REPLY).toString()
            binding.textViewReplyDirect.text = message
        }
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NotificationsActivity.NOTIFICATION_ID)
    }
}