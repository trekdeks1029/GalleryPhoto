package com.alpha.galleryphoto.domain.model.data

import io.realm.RealmObject

data class User(var login: String = "", var password: String = "") {

    var token: String? = null
    var id: Int = 0

    constructor(id: Int = 0, login: String, password: String) : this() {
        this.id = id
        this.login = login
        this.password = password
    }

    constructor(user: UserDb) : this() {
        id = user.id
        login = user.login
        password = user.password
    }

}

open class UserDb() : RealmObject() {

    var id: Int = 0
    var login: String = ""
    var password: String = ""
    var my = 1

    constructor(user: User) : this() {
        this.id = user.id
        this.login = user.login
        this.password = user.password
    }
}