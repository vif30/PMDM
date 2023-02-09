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

class Notifications(private val context: Context, private val title:String, private val shortText:String?, private val longText:String? ) {
    private val CHANNEL_NAME = "NotificationsClient1"
    private val CHANNEL_ID: String = "Notification_ID_client1"
    private val NOTIFICATION_ID: Int = Random.nextInt()

    private lateinit var pendingIntent: PendingIntent

    init{
        makePendingIntent()
        makeNotificationChannel()
        makeNotificaton()
    }

    private fun makePendingIntent() {
        val intent = Intent(context,MainActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_MULTIPLE_TASK
        pendingIntent = PendingIntent.getActivity(context, 0,intent,0)
    }

    private fun makeNotificationChannel() {
        //Notifications channels are just available from Oreo version and above.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Features of channel
            val notificationChannel = NotificationChannel(CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT)

            val notificationManager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager

            //Notification is visible on the App's icon
            notificationChannel.setShowBadge(true)

            //Creates the notification
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun makeNotificaton() {
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(context, CHANNEL_ID)


        with(builder){
            setSmallIcon(R.drawable.ic_email_icon)
            setContentTitle(title)
            setContentText(shortText?:"")
            setStyle(
                NotificationCompat.BigTextStyle()
                .bigText(longText))


            color = Color.BLUE
            priority = NotificationCompat.PRIORITY_DEFAULT

            //1 sec on 1 sec off
            setLights(Color.MAGENTA, 1000, 1000)

            //1 sec on 1 sec off 1 sec on 1 sec off
            setVibrate(longArrayOf(1000, 1000, 1000, 1000))

            //setSound()
            setDefaults(Notification.DEFAULT_SOUND)

            //When we click on the notification
            setContentIntent(pendingIntent)

            setVisibility(NotificationCompat.VISIBILITY_PUBLIC)


            //setTimeoutAfter(5000L) //cancels notification after 5 sec
            setAutoCancel(true)
        }

        val notificationManagerCompat = NotificationManagerCompat.from(
            context)

        //Launch the notification
        notificationManagerCompat.notify(NOTIFICATION_ID , builder.build())
    }
}