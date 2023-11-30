package com.example.instagram.login.data

import com.example.instagram.common.view.model.UserAuth

interface LoginCallback {
    fun onSuccess()
    fun onFailure(messager:String)
    fun onComplete()

}
