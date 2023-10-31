package com.example.instagram.register.data

interface RegisterCallback {
    fun onSuccess()
    fun onFailure(messager: String)
    fun onComplete()

}
