package com.example.classwork11

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast


class RootReceiver : BroadcastReceiver(){
    companion object {
        const val MY_CUSTOM_ACTION = "MY_CUSTOM_ACTION"
        const val MESSAGE_VALUE = "MESSAGE_VALUE"
        const val RECEIVER_NAME = "My Receiver"
    }

    override fun onReceive(context: Context, intent: Intent) {
        Toast.makeText(context, intent.getStringExtra(MESSAGE_VALUE), Toast.LENGTH_LONG).show()
    }
}