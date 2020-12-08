package com.example.crudrealmlabb

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crudrealmlabb.model.Message
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmResults
import io.realm.kotlin.createObject
import io.realm.kotlin.where

enum class State {
    CREATE,
    EDIT,
    SENT,
    FAILED
}

class MessageViewModel: ViewModel() {
    var state = MutableLiveData<State>()
    var message : Message? = null

    fun createMessage() {
        try {
            message?.let { RealmManager.createMessage(it) }
        }
        catch(e: Error) {
            state.value = State.FAILED
        }
        finally {
            state.value = State.SENT
        }
    }

    fun updateMessage() {
        try {
            message?.let { RealmManager.updateMessage(it) }
        }
        catch(e: Error) {
            state.value = State.FAILED
        }
        finally {
            state.value = State.SENT
        }
    }

    fun checkEdit(id: String?) {
        if (id != null) {
            state.value = State.EDIT
        } else {
            state.value = State.CREATE
        }
    }
}