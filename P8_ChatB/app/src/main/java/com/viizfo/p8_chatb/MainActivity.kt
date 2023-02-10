package com.viizfo.p8_chatb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.p8_chatb.adapter.MessageAdapter
import com.viizfo.p8_chatb.viewModel.ChatViewModel
import com.viizfo.p8_chatb.databinding.ActivityMainBinding
import com.viizfo.p8_chatb.model.Message
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatViewModel: ChatViewModel
    private lateinit var adapter: MessageAdapter
    private val messageList = mutableListOf<Message>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivityMainBinding.inflate(layoutInflater).also { binding = it }.root)
        initRecyclerView()


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

        binding.btnSend.setOnClickListener {

            if(!binding.etChat.text.isNullOrEmpty()){
                val sdf = SimpleDateFormat("dd/MM/yy hh:mm")
                val date = sdf.format(Date())
                val mssg = binding.etChat.text.toString()
                val message = Message(mssg, date, 1)
                chatViewModel.sendMessage(message)
            }
        }

    }

    private fun initRecyclerView() {
        adapter = MessageAdapter(messageList)
        binding.rvChat.layoutManager = LinearLayoutManager(this)
        binding.rvChat.adapter = adapter
    }

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