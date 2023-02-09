package com.viizfo.p8_chatb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModelProvider
import com.viizfo.p8_chata.ViewModel.ChatViewModel
import com.viizfo.p8_chatb.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatViewModel: ChatViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]

        chatViewModel.myMessageLD.observe(this){
                message ->
            upload(message, true)
        }

        chatViewModel.messageLD.observe(this){
                messageList ->
            for(message:String in messageList)
                upload(message, false)
            chatViewModel.notifyDeliver()
        }

        binding.btnSend.setOnClickListener {

            if(!binding.etChat.text.isNullOrEmpty()){
                chatViewModel.sendMessage(binding.etChat.text.toString())
            }
        }

        /*sendDelayedFunction(200){ */hideKeyboard()
    }

    private fun upload(message:String, isOwn:Boolean = true) {
        val tv = TextView(this)
        tv.text = HtmlCompat.fromHtml("${message}", HtmlCompat.FROM_HTML_MODE_COMPACT)
        if(isOwn) tv.textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        else tv.textAlignment = View.TEXT_ALIGNMENT_VIEW_START

        /*//Adding Style
        if (Build.VERSION.SDK_INT >= 23) {
            tv.setTextAppearance(R.style.TextChat);
        } else {
            tv.setTextAppearance(tv.context, R.style.TextChat);
        }*/

        binding.linearChat.addView(tv)

        binding.etChat.setText("")
        //hideKeyboard()

        //updateScroll()

    }


    private fun hideKeyboard() {
        val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
    }

    private fun updateScroll(){
        binding.svChat.fullScroll(View.FOCUS_DOWN)
    }
}