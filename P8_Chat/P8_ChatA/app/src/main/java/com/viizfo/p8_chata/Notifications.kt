package com.viizfo.p8_chata

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlin.random.Random

//Class that sends the notifications to Android OS
class Notifications(private val context: Context, private val title:String, private val shortText:String?, private val longText:String? ) {
    private val NOTIFICATION_ID: Int = Random.nextInt()
    private lateinit var pendingIntent: PendingIntent
    private val CHANNEL_NAME = "NotificationsClientA"
    private val CHANNEL_ID: String = "Notification_ID_clientA"
    init{
        makePendingIntent()
        makeNotificationChannel()
        makeNotification()
    }
    private fun makePendingIntent() {
        val intent = Intent(context,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        pendingIntent = PendingIntent.getActivity(context, 0,intent,0)
    }
    private fun makeNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager
            notificationChannel.setShowBadge(true)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
    private fun makeNotification() {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, CHANNEL_ID)
        with(builder){
            setSmallIcon(R.drawable.sobre)
            setContentTitle(title)
            setContentText(shortText?:"")
            setStyle(
                NotificationCompat.BigTextStyle()
                .bigText(longText))
            color = Color.RED
            priority = NotificationCompat.PRIORITY_DEFAULT
            setContentIntent(pendingIntent)
            setDefaults(Notification.DEFAULT_SOUND)
            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            setAutoCancel(true)
        }
        val notificationManagerCompat = NotificationManagerCompat.from(
            context)
        notificationManagerCompat.notify(NOTIFICATION_ID , builder.build())
    }
}