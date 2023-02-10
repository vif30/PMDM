package com.viizfo.p8_chata.viewModel

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.viizfo.p8_chata.MyBroadcastReceiver
import com.viizfo.p8_chata.MyBroadcastReceiver.Companion.MY_ACTION_RECEIVER_ACTION
import com.viizfo.p8_chata.MyBroadcastReceiver.Companion.OTHER_ACTION_RECEIVER_ACTION
import com.viizfo.p8_chata.MyBroadcastReceiver.Companion.OTHER_ACTION_RECEIVER_EXTRA
import com.viizfo.p8_chata.model.Message
import java.text.SimpleDateFormat
import java.util.*

class ChatViewModel(application: Application):AndroidViewModel(application) {
    val context: Context = application
    private lateinit var br: BroadcastReceiver
    val messageLD = MutableLiveData<MutableList<Message>>()
    val myMessageLD = MutableLiveData<Message>()
    private val messageList = mutableListOf<Message>()
    init{
        bindReceiver()
    }
    //Function that receives the messages
    private fun bindReceiver(){
        IntentFilter().apply {
            addAction(MY_ACTION_RECEIVER_ACTION)
            br = MyBroadcastReceiver(){
                val msg = it
                val sdf = SimpleDateFormat("dd/MM/yy hh:mm")
                val date = sdf.format(Date())
                val message = Message(msg, date, 2)
                onMessageReceived(message)
            }
            context.registerReceiver(br, this)
        }
    }
    //Function that sends the message to the other app
    fun sendMessage(message: Message){
        Intent().apply {
            action = OTHER_ACTION_RECEIVER_ACTION
            putExtra(OTHER_ACTION_RECEIVER_EXTRA, message.text)
            context.sendOrderedBroadcast(this, null)
        }
        myOwnMessageSent(message)
    }
    //Function that adds the message we send in our app
    private fun myOwnMessageSent(message: Message){
        myMessageLD.postValue(message)
    }
    //Function that add the message we received in our app
    private fun onMessageReceived(message: Message){
        messageList.add(message)
        messageLD.postValue(messageList)
    }
    fun notifyDeliver(){
        messageList.clear()
    }
}