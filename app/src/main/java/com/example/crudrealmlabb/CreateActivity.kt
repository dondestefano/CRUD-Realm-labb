package com.example.crudrealmlabb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.example.crudrealmlabb.model.Message
import kotlinx.android.synthetic.main.message_view.*
import org.w3c.dom.Text

class CreateActivity : AppCompatActivity() {
    private val messageViewModel: MessageViewModel by viewModels()

    private var messageId: String? = null

    private lateinit var title: TextView
    private lateinit var titleEditText: EditText
    private lateinit var bodyEditText: EditText
    private lateinit var submitButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        title = findViewById(R.id.messageTextView)
        titleEditText = findViewById(R.id.messageTitleEditText)
        bodyEditText = findViewById(R.id.messageBodyEditText)
        submitButton = findViewById(R.id.submitButton)

        getExtra()
        observeViewModel()
        messageViewModel.checkEdit(messageId)
    }

    private fun observeViewModel() {
        messageViewModel.state.observe(this, Observer {
            when (it) {
                State.CREATE -> {
                    title.text = "Input new message"
                    submitButton.text = "Submit"

                    submitButton.setOnClickListener {
                        submitMessage()
                    }
                }
                State.EDIT -> {
                    title.text = "Edit message"
                    submitButton.text = "Save"

                    titleEditText.setText(intent.getStringExtra("editTitle"))
                    bodyEditText.setText(intent.getStringExtra("editBody"))

                    submitButton.setOnClickListener {
                        messageViewModel.updateMessage(
                                messageId!!,
                                titleEditText.text.toString(),
                                bodyEditText.text.toString())
                    }
                }
                State.SENT -> {
                    finish()
                }
                State.FAILED -> {
                    title.text = "Something went wrong"
                }
            }
        })
    }

    private fun getExtra(){
        messageId = intent.getStringExtra("editId")
    }

    private fun submitMessage() {
        val newMessage = Message(titleEditText.text.toString(), bodyEditText.text.toString())
        messageViewModel.createMessage(newMessage)
    }

    override fun onDestroy() {
        super.onDestroy()
        messageViewModel.onBackPressed()
    }
}