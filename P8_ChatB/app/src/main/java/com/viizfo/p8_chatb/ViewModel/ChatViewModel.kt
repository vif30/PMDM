package com.viizfo.p8_chata.ViewModel

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
import java.text.SimpleDateFormat
import java.util.*

class ChatViewModel(application: Application):AndroidViewModel(application) {
    val context: Context = application

    private lateinit var br: BroadcastReceiver
    val messageLD = MutableLiveData<MutableList<String>>()
    val myMessageLD = MutableLiveData<String>()

    private val messageList = mutableListOf<String>()

    init{
        bindReceiver()
    }

    private fun bindReceiver(){
        IntentFilter().apply {
            addAction(MY_ACTION_RECEIVER_ACTION)
            br = MyBroadcastReceiver(){
                onMessageReceived(it)
            }
            context.registerReceiver(br, this)
        }
    }

    //Send the broadcast
    fun sendMessage(message:String){
        Intent().apply {
            action = OTHER_ACTION_RECEIVER_ACTION
            putExtra(OTHER_ACTION_RECEIVER_EXTRA, message)
            context.sendOrderedBroadcast(this, null)
        }
        myOwnMessageSent(message)
    }

    private fun myOwnMessageSent(message: String){
        //Maybe data formatting and text style belong to the View
        myMessageLD.postValue(message)
    }

    private fun onMessageReceived(message:String){
        val date = SimpleDateFormat("dd/mm/yy hh:mm")
        messageList.add(date.format(Date()) + message)
        messageLD.postValue(messageList)
    }
    fun notifyDeliver(){
        messageList.clear()
    }

    override fun onCleared() {
        super.onCleared()
        context.unregisterReceiver(br)
    }
}