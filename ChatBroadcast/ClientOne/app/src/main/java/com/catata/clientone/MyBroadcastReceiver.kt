package com.catata.clientone

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.lang.StringBuilder

typealias onMessageReceived = (message:String)->Unit

class MyBroadcastReceiver():BroadcastReceiver() {
    private val TAG = "MyBroadcastReceiver"
    private lateinit var context:Context
    private var onMessageReceived: onMessageReceived? = null

    override fun onReceive(ctx: Context?, intent: Intent?) {
        context = ctx!!
        when(intent?.action){
            MY_ACTION_RECEIVER_ACTION -> {
                onMessageReceived?.let { it("${intent.getStringExtra(MY_ACTION_RECEIVER_EXTRA)}") }
                sendLog("A message is incoming \n ${intent.getStringExtra(MY_ACTION_RECEIVER_EXTRA)}")
                MyNotifications(context, "Client One", "Message from Client two","${intent.getStringExtra(MY_ACTION_RECEIVER_EXTRA)}" )
            }
        }
    }

    constructor(mOnMessageReceived: onMessageReceived):this(){
        onMessageReceived = mOnMessageReceived
    }

    private fun sendLog(s: String) {
        Log.d(TAG,s)
    }

    companion object{
        const val MY_ACTION_RECEIVER_ACTION = "com.catata.clientone.ACTION_RECEIVER"
        const val MY_ACTION_RECEIVER_EXTRA = "com.catata.clientone.RECEIVER_EXTRA"
        const val OTHER_ACTION_RECEIVER_ACTION = "com.catata.clienttwo.ACTION_RECEIVER"
        const val OTHER_ACTION_RECEIVER_EXTRA = "com.catata.clienttwo.RECEIVER_EXTRA"



    }


}