package com.viizfo.p8_chata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.viizfo.p8_chata.ViewModel.ChatViewModel
import com.viizfo.p8_chata.adapter.MessageAdapter
import com.viizfo.p8_chata.databinding.ActivityMainBinding
import com.viizfo.p8_chata.model.Message
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

        /*sendDelayedFunction(200){ */hideKeyboard()
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
    }


    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

}