package com.example.instagram.register.data

interface RegisterEmailDataSource {
    fun create(email:String,callback: RegisterEmailCallback)
}