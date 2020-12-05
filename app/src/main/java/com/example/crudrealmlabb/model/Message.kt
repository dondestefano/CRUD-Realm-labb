package com.example.crudrealmlabb.model

import io.realm.RealmObject
import io.realm.annotations.Required

open class Message (
    @Required
    var title: String,
    @Required
    var body: String,
): RealmObject()