package com.example.socket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.socket.databinding.MessageMeRecyclerviewItemBinding

data class Chatting(
    val text: String = "Hello"
)

class MessageAdapter(val data: MutableList<Chatting>): RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {


    class MessageViewHolder(val binding: MessageMeRecyclerviewItemBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_me_recyclerview_item, parent, false)
        return MessageViewHolder(MessageMeRecyclerviewItemBinding.bind(view))
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val listChatting = data[position]
        holder.binding.chatText.text = listChatting.text
    }
}