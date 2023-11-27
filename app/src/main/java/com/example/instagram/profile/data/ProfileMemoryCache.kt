package com.example.instagram.profile.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.model.UserAuth

object ProfileMemoryCache : Cache<Pair<UserAuth, Boolean?>>{
    private var userAuth: Pair<UserAuth, Boolean?>? = null
    override fun iscached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String):Pair<UserAuth, Boolean?>? {
        if (userAuth?.first?.uuid == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: Pair<UserAuth, Boolean?>?) {
        userAuth = data

    }
}