package com.example.instagram.profile.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.model.UserAuth

object ProfileMemoryCache : Cache<UserAuth> {
    private var userAuth: UserAuth? = null
    override fun iscached(): Boolean {
        return userAuth != null
    }

    override fun get(key: String): UserAuth? {
        if (userAuth?.uuid == key) {
            return userAuth
        }
        return null
    }

    override fun put(data: UserAuth?) {
        userAuth = data

    }
}