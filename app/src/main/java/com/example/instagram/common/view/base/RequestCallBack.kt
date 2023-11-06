package com.example.instagram.common.view.base

interface RequestCallBack<T> {
    fun onSuccess(data:T)
    fun onFailure(message:String)
    fun onComplete()
}