package com.example.crudrealmlabb.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudrealmlabb.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import kotlinx.android.synthetic.main.message_view.view.*

class MessageAdapter(data: OrderedRealmCollection<Message>) : RealmRecyclerViewAdapter<Message, MessageAdapter.MessageViewHolder?>(data, true) {

    var onDeleteListener: (Message?) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val messageView: View = LayoutInflater.from(parent.context).inflate(R.layout.message_view, parent, false)
        return MessageViewHolder(messageView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val obj: Message? = getItem(position)
        if (obj != null) {
            holder.bind(obj)
        }
    }


    inner class MessageViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        var titleTextView: TextView = view.messageTitle
        var bodyTextView: TextView = view.messageBody
        private val deleteButton: Button = view.deleteMessageButton

        fun bind(obj: Message) = with(view){
            titleTextView.text = obj.title
            bodyTextView.text = obj.body

            deleteButton.setOnClickListener { onDeleteListener(data?.get(adapterPosition))  }
        }
    }
}