package com.example.instagram.login.data

interface LoginCallback {
    fun onSuccess()
    fun onFailure(messager:String)
    fun onComplete()

}
