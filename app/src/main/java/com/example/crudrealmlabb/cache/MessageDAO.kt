package com.example.crudrealmlabb.cache

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.crudrealmlabb.model.Message

@Dao
interface MessageDAO {
    @Query("SELECT * FROM message")
    fun getAll(): LiveData<List<Message>>

    @Query("SELECT * from message WHERE id = :key")
    fun get(key: String): Message?

    @Query("SELECT * FROM message WHERE id IN (:messageIds)")
    fun loadAllByIds(messageIds: Array<String>): List<Message>

    @Insert
    fun insert (message: Message)

    @Insert
    fun insertAll(vararg messages: Message)

    @Delete
    fun delete(message: Message)

    @Query("DELETE FROM message")
    fun deleteAll()
}