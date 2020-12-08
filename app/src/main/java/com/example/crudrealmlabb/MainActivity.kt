package com.example.crudrealmlabb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.crudrealmlabb.model.Message
import com.example.crudrealmlabb.model.MessageAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.kotlin.where

class MainActivity : AppCompatActivity() {
    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var createButton: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RealmManager.startDefaultRealm()

        messageRecyclerView = findViewById(R.id.messageRecyclerView)
        createButton = findViewById(R.id.newMessageButton)

        RealmManager.searchForMessages()
        initRecycleView()
        initButton()
    }

    private fun initRecycleView() {
        messageRecyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = MessageAdapter(RealmManager.getMessages())
        adapter.onDeleteListener = {onDelete(it)}
        adapter.onEditListener = { onEdit(it) }
        messageRecyclerView.adapter = adapter
    }

    private fun initButton() {
        createButton.setOnClickListener{
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun onDelete(message: Message?) {
        if (message != null) {
            RealmManager.deleteMessage(message)
        }
    }

    private fun onEdit(message: Message?) {
        message.let {
            val intent = Intent(this, CreateActivity::class.java)
            intent.putExtra("editId", message?.id)
            intent.putExtra("editTitle", message?.title)
            intent.putExtra("editBody", message?.body)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        RealmManager.closeRealm()
    }
}