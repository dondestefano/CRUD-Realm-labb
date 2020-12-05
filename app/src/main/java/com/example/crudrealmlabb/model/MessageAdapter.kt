package com.example.crudrealmlabb.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.crudrealmlabb.R
import io.realm.OrderedRealmCollection
import io.realm.RealmRecyclerViewAdapter
import org.w3c.dom.Text

class MessageAdapter(data: OrderedRealmCollection<Message>) : RealmRecyclerViewAdapter<Message, MessageAdapter.MessageViewHolder?>(data, true) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val messageView: View = LayoutInflater.from(parent.context).inflate(R.layout.message_view, parent, false)
        return MessageViewHolder(messageView)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val obj: Message? = getItem(position)
        holder.titleTextView.text = obj?.title
        holder.bodyTextView.text = obj?.body
    }

    inner class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var titleTextView: TextView = view.findViewById(R.id.messageTitle)
        var bodyTextView: TextView = view.findViewById(R.id.messageBody)
    }
}