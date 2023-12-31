package com.example.instagram.home.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

interface HomeDataSource {
    fun logout() {throw UnsupportedOperationException()}
    fun fetchFeed(uuid: String, callBack: RequestCallBack<List<Post>>)
    fun fetchSession():String{throw UnsupportedOperationException()}
    fun putFeed(response:List<Post>?){throw UnsupportedOperationException()}
}