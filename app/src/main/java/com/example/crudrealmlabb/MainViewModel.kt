package com.example.crudrealmlabb

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.crudrealmlabb.cache.CacheDatabase
import com.example.crudrealmlabb.cache.MessageDAO
import com.example.crudrealmlabb.model.Message
import io.realm.OrderedRealmCollection

class MainViewModel: ViewModel() {
    private lateinit var messageDao: MessageDAO
    private lateinit var db: CacheDatabase

    // TODO: This needs to have Kapt instead of annotationProcessor but Realm crashes the app if Kapt is used.
    fun startCacheDb(context: Context) {
        db = CacheDatabase.getInstance(context)
        messageDao = db.messageDao
    }

    fun saveToCache(messages: OrderedRealmCollection<Message>) {
        for (message in messages){
            messageDao.insert(message)
        }
    }

    fun clearCache() {
        messageDao.deleteAll()
    }
}