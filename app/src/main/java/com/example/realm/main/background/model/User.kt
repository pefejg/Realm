package com.example.realm.main.background.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class User(
    @PrimaryKey
    var id: Int? = 0,
    var name: String? = "",
    var parentLastName: String? = "",
    var lastName: String? = "",
    var birthDate: String? = "",
    var country: String? = ""
): RealmObject() {
}