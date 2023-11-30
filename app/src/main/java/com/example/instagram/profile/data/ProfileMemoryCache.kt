package com.example.instagram.profile.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth

object ProfileMemoryCache : Cache<Pair<User, Boolean?>>{
    private var userAuth: Pair<User, Boolean?>? = null
    override fun iscached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String):Pair<User, Boolean?>? {
        if (userAuth?.first?.uuid == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<User, Boolean?>?) {
        userAuth = data

    }
}