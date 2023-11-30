package com.example.instagram.common.view.model

import android.net.Uri

data class Post(
    val UUID: String?=null,
    val photourl: String?=null,
    val caption:String?=null,
    val timestamp: Long=0,
    val publisher: User?=null
)
