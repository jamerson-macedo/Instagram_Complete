package com.example.instagram.common.view.base

interface Cache<T> {
    // verificar se tem algo em cache
    fun iscached():Boolean
    fun get(key:String):T?
    fun put(data:T?)
}