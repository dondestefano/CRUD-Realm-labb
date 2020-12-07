package com.example.crudrealmlabb.model

import io.realm.RealmObject
import io.realm.annotations.Required
import java.lang.Math.random

open class Message (
    var title: String = "",
    var body: String = "",
): RealmObject() {
    var id: String? = random().toString()
}