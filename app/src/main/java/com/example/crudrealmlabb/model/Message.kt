package com.example.crudrealmlabb.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.realm.RealmObject
import io.realm.annotations.Required
import java.lang.Math.random

@Entity(tableName = "message")
open class Message (
    @ColumnInfo (name= "title") var title: String = "",
    @ColumnInfo (name= "body")var body: String = "",
): RealmObject() {
    @PrimaryKey
    var id: String? = random().toString()
}