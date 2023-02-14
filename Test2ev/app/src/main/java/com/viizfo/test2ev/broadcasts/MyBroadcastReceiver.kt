package com.viizfo.test2ev.broadcasts

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Settings
import android.widget.Toast
import com.viizfo.test2ev.notifications.Notifications


class MyBroadcastReceiver:BroadcastReceiver() {
    private val TAG = "MyBroadcastReceiver"
    private lateinit var context:Context

    override fun onReceive(ctx: Context?, intent: Intent?) {
        this.context = context!!
        when(intent?.action){
            ACTION_AIRPLANE_MODE ->{
                if(isOnAirPlaneMode()){
                    /*Notifications(this.context, "Chat A", "Message from Chat B","${intent.getStringExtra(MY_ACTION_RECEIVER_EXTRA)}" )*/
                    sendMessage("AIRPLANE MODE ON")
                }else{
                    sendMessage("AIRPLANE MODE OFF")
                }
            }
        }
    }
    private fun sendMessage(s: String) {
        Toast.makeText(context,s, Toast.LENGTH_SHORT).show()
    }
    private fun isOnAirPlaneMode(): Boolean {
        return (Settings.Global.getInt(context.contentResolver,
            Settings.Global.AIRPLANE_MODE_ON, 0) != 0)
    }

    companion object{
        val MY_ACTION_RECEIVER_ACTION = MyBroadcastReceiver::class.java.canonicalName + ".ACTION_RECEIVER"
        val MY_ACTION_RECEIVER_EXTRA = MyBroadcastReceiver::class.java.canonicalName + ".RECEIVER_EXTRA"
        const val ACTION_AIRPLANE_MODE = "android.intent.action.ACTION_AIRPLANE_MODE"

    }


}