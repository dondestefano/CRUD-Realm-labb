package com.example.crudrealmlabb

import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration


class RealmMigrations : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema
        if (oldVersion == 2L) {
            val messageSchema = schema["Message"]
                messageSchema!!.addField("id", String::class.java)
            }
        }
    }
