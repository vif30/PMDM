package com.viizfo.p8_chata.adapter

import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.viizfo.p8_chata.R
import com.viizfo.p8_chata.databinding.ActivityMainBinding
import com.viizfo.p8_chata.databinding.ItemMessageBinding
import com.viizfo.p8_chata.model.Message

class MessageAdapter(private val messageList: MutableList<Message>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MessageViewHolder(layoutInflater.inflate(R.layout.item_message, parent, false))
    }
    override fun getItemCount(): Int = messageList.size
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val item = messageList[position]
        if (item != null) {
            holder.bind(item)
        }
    }

    inner class MessageViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = ItemMessageBinding.bind(view)
        //formatting the messages depending on the owner
        fun bind (message: Message) {
            binding.tvMessage.text = message.text
            binding.tvDate.text = message.date
            if(message.owner == 1){
                binding.tvMessage.setTextColor(Color.RED)
                binding.tvMessage.gravity = Gravity.RIGHT
                binding.tvDate.gravity = Gravity.RIGHT
            }else {
                binding.tvMessage.setTextColor(Color.BLUE)
                binding.tvMessage.gravity = Gravity.LEFT
                binding.tvDate.gravity = Gravity.LEFT
            }
        }
    }
}