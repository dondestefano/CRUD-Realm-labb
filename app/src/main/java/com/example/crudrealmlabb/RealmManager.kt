package com.example.crudrealmlabb

import com.example.crudrealmlabb.model.Message
import io.realm.OrderedRealmCollection
import io.realm.Realm
import io.realm.kotlin.where

object RealmManager {
    private lateinit var realm: Realm
    private lateinit var messages: OrderedRealmCollection<Message>

    fun startDefaultRealm() {
        realm = Realm.getDefaultInstance()
    }

    fun searchForMessages() {
        messages = realm.where<Message>().findAllAsync()
    }

    fun getMessages(): OrderedRealmCollection<Message> { return messages }

    fun createMessage(message: Message) {
        realm.executeTransaction {
            realm.copyToRealm(message)
        }
    }

    fun deleteMessage(message: Message) {
        realm.executeTransaction {
            message.deleteFromRealm()
        }
    }

    fun updateMessage(message: Message) {
        realm.executeTransaction {
            val messageToEdit = realm.where<Message>().equalTo("id", message.id).findFirst()
            messageToEdit?.title = message.title
            messageToEdit?.body = message.body
        }
    }

    fun closeRealm() {
        realm.close()
    }
}