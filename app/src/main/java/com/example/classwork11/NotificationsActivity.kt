package com.example.classwork11


import android.R.id
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.RemoteInput
import com.example.classwork11.databinding.ActivityNotificationsBinding


class NotificationsActivity : AppCompatActivity() {

    companion object {
        private const val CHANNEL_ID = "NOTIFICATIONS_CHANNEL"
        const val NOTIFICATION_ID = 1001
        const val TXT_REPLY = "text_reply"
        private const val NOTIFICATION_LOG_TAG = "NOTIFICATION_LOG_TAG"
        fun start(context: Context) {
            context.startActivity(Intent(context, NotificationsActivity::class.java))
        }
    }

    private lateinit var binding: ActivityNotificationsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        setupListeners()
    }

    private fun setupBinding() {
        binding = ActivityNotificationsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupListeners() {
        binding.btnSimpleNotification.setOnClickListener {
            Log.d(NOTIFICATION_LOG_TAG,"binding.btnSimpleNotification - click")
            sendSimpleNotification()
        }
        binding.btnNotificationWithAction.setOnClickListener {
            Log.d(NOTIFICATION_LOG_TAG,"binding.btnNotificationWithAction - click")
            sendNotificationWithAction()
        }
        binding.btnNotificationWithReply.setOnClickListener {
            Log.d(NOTIFICATION_LOG_TAG,"binding.btnNotificationWithReply - click")
            sendNotificationWithReply()
        }
        binding.btnNotificationWithProgress.setOnClickListener {
            Log.d(NOTIFICATION_LOG_TAG,"binding.btnNotificationWithProgress - click")
            sendNotificationWithProgress()
        }
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, "name", importance).apply {
                description = "descriptionText"
                setSound(null,null)
            }
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendSimpleNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Simple notification title")
            .setContentText("Simple notification message")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Simple notification message")
            )
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        createNotificationChannel()
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
    }

    private fun sendNotificationWithAction() {
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            0
        )

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Notification title with action")
            .setContentText("Notification message with action")
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText("Notification message with action")
            )
            .addAction(R.drawable.ic_launcher_foreground, "Action Title", pendingIntent)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        createNotificationChannel()
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
    }

    private fun sendNotificationWithReply() {

        val intent = Intent(this, NotificationFromReplyActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_download)
            .setContentTitle("Notification with reply title")
            .setContentText("Notification with reply")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val remoteInput = RemoteInput.Builder(TXT_REPLY).setLabel("Reply").build()

        val replyIntent = Intent(this, NotificationFromReplyActivity::class.java)

        replyIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

        val replyPendingIntent =
            PendingIntent.getActivity(
                this,
                0,
                replyIntent,
                PendingIntent.FLAG_CANCEL_CURRENT
            )

        val action = NotificationCompat.Action
            .Builder(R.drawable.ic_launcher_background, "Reply", replyPendingIntent)
            .addRemoteInput(remoteInput)
            .build()

        builder.addAction(action)
        createNotificationChannel()
        NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
    }

    private fun sendNotificationWithProgress() {

        val builder = NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle("Picture Download")
            setContentText("Download in progress")
            setSmallIcon(R.drawable.ic_download)
            setOnlyAlertOnce(true)
            priority = NotificationCompat.PRIORITY_DEFAULT
        }
        val PROGRESS_MAX = 100
        val PROGRESS_CURRENT = 0
        builder.setProgress(PROGRESS_MAX, PROGRESS_CURRENT, false)

        val thread = Thread(
            Runnable {
                var porgressCurrent = 0
                while (porgressCurrent <= 100) {
                    Thread.sleep(1000)
                    builder.setProgress(PROGRESS_MAX, porgressCurrent, false)
                    NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
                    porgressCurrent += 10
                }
                builder.setContentText("Download finished")
                builder.setProgress(0, 0, false)
                NotificationManagerCompat.from(this).notify(NOTIFICATION_ID, builder.build())
            }
        )
        createNotificationChannel()
        thread.start()
    }
}



