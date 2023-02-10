package com.viizfo.p8_chatb

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.viizfo.p8_chata.Notifications

typealias onMessageReceived = (message:String)->Unit

class MyBroadcastReceiver() : BroadcastReceiver() {
    private val TAG = "MyBroadcastReceiver"
    private lateinit var context:Context
    private var onMessageReceived: onMessageReceived? = null
    override fun onReceive(context: Context?, intent: Intent?) {
        this.context = context!!
        when(intent?.action){
            MY_ACTION_RECEIVER_ACTION -> {
                onMessageReceived?.let { it("${intent.getStringExtra(MY_ACTION_RECEIVER_EXTRA)}") }
                Notifications(this.context, "Chat A", "Message from Chat B","${intent.getStringExtra(MY_ACTION_RECEIVER_EXTRA)}" )
            }
        }
    }
    constructor(mOnMessageReceived: onMessageReceived):this(){
        onMessageReceived = mOnMessageReceived
    }
    companion object{
        const val MY_ACTION_RECEIVER_ACTION = "com.viizfo.chatb.ACTION_RECEIVER"
        const val MY_ACTION_RECEIVER_EXTRA = "com.viizfo.chatb.RECEIVER_EXTRA"
        const val OTHER_ACTION_RECEIVER_ACTION = "com.viizfo.chata.ACTION_RECEIVER"
        const val OTHER_ACTION_RECEIVER_EXTRA = "com.viizfo.chata.RECEIVER_EXTRA"


    }
}