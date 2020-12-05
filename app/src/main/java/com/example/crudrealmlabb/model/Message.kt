package com.example.crudrealmlabb.model

import io.realm.RealmObject
import io.realm.annotations.Required

open class Message (
    var title: String = "",
    var body: String = ""
): RealmObject()