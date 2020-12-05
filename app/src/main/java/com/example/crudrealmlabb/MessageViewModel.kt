package com.example.crudrealmlabb

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crudrealmlabb.model.Message
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.createObject

enum class State {
    IDLE,
    SENT,
    FAILED
}

class MessageViewModel: ViewModel() {

    var state = MutableLiveData<State>()
    private val realm = Realm.getDefaultInstance()

    fun createMessage(message: Message) {
        try {
            realm.executeTransaction {
                realm.copyToRealm(message)
            }
        } catch(e: Error) {
            state.value = State.FAILED
        }
        finally {
            realm.close()
            state.value = State.SENT
        }
    }
}