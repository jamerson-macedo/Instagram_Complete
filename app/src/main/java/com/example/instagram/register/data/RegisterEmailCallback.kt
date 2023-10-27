package com.example.instagram.register.data

import com.example.instagram.common.view.model.UserAuth

interface RegisterEmailCallback {
    fun onSuccess()
    fun onFailure(messager: String)
    fun onComplete()

}
