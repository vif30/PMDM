package com.viizfo.p8_chata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.p8_chata.viewModel.ChatViewModel
import com.viizfo.p8_chata.adapter.MessageAdapter
import com.viizfo.p8_chata.databinding.ActivityMainBinding
import com.viizfo.p8_chata.model.Message
import java.text.SimpleDateFormat
import java.util.*

/*This is a chat app, which communicates with a sister app, when a message is sent between them, the receiving app
triggers a notification. If we click on it, we enter the receiving chat and we can see the message. This is the Chat A*/

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var adapter: MessageAdapter
    private val messageList = mutableListOf<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        //loading the recyclerView
        initRecyclerView()
        //loading the viewmodel
        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        chatViewModel.myMessageLD.observe(this){
                message ->
            upload(message, true)
        }
        chatViewModel.messageLD.observe(this){
                messageList ->
            for(message:Message in messageList)
                upload(message, false)
            chatViewModel.notifyDeliver()
        }
        //setting the listener on the button
        binding.btnSend.setOnClickListener {
            if(!binding.etChat.text.isNullOrEmpty()){
                val sdf = SimpleDateFormat("dd/MM/yy hh:mm")
                val date = sdf.format(Date())
                val msg = binding.etChat.text.toString()
                val message = Message(msg, date, 1)
                chatViewModel.sendMessage(message)
            }
        }
    }
    //function to load the recyclerView
    private fun initRecyclerView() {
        adapter = MessageAdapter(messageList)
        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.adapter = adapter
    }
    //function to upload the new messages
    private fun upload(message:Message, isOwn:Boolean = true) {
        val messagelist = mutableListOf<Message>()
        messagelist.add(message)
        if(messagelist.isNotEmpty()){
            messageList.addAll(messagelist)
            adapter.notifyDataSetChanged()
        }
        binding.etChat.setText("")
        binding.rvChat.scrollToPosition(messageList.size - 1)
    }
}