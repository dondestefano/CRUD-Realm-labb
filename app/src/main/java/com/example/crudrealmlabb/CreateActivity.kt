package com.example.crudrealmlabb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.crudrealmlabb.model.Message

class CreateActivity : AppCompatActivity() {
    private val messageViewModel: MessageViewModel by viewModels()

    private lateinit var titleEditText: EditText
    private lateinit var bodyEditText: EditText
    private lateinit var submitButton: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        titleEditText = findViewById(R.id.messageTitleEditText)
        bodyEditText = findViewById(R.id.messageBodyEditText)
        submitButton = findViewById(R.id.submitButton)

        submitButton.setOnClickListener {
            submitMessage()
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        messageViewModel.state.observe(this, Observer {
            when (it) {
                State.IDLE -> {
                }
                State.SENT -> {
                    finish()
                }
                State.FAILED -> {
                }
            }
        })
    }

    private fun submitMessage() {
        val newMessage = Message(titleEditText.text.toString(), bodyEditText.text.toString())
        messageViewModel.createMessage(newMessage)
    }
}