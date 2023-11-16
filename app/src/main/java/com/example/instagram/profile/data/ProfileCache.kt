package com.example.instagram.profile.data

interface ProfileCache<T> {
    // verificar se tem algo em cache
    fun iscached():Boolean
    fun get(key:String):T?
    fun put(data:T?)
}