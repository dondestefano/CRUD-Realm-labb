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
    private lateinit var realm: Realm
    private lateinit var messages: OrderedRealmCollection<Message>

    private lateinit var messageRecyclerView: RecyclerView
    private lateinit var createButton: FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        realm = Realm.getDefaultInstance()

        messageRecyclerView = findViewById(R.id.messageRecyclerView)
        createButton = findViewById(R.id.newMessageButton)

        getMessages()
        initRecycleView()
        initButton()
    }

    private fun getMessages() {
        messages = realm.where<Message>().findAllAsync()
    }

    private fun initRecycleView() {
        val adapter = MessageAdapter(messages)
        messageRecyclerView.layoutManager = LinearLayoutManager(this)
        messageRecyclerView.adapter = adapter
    }

    private fun initButton() {
        createButton.setOnClickListener{
            val intent = Intent(this, CreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}