package com.example.instagram.home.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

object FeedMemoryCache : Cache<List<Post>> {
    private var posts:List<Post>?=null
    override fun iscached(): Boolean {
        return posts!=null
    }

    override fun get(key: String): List<Post>? {
       return posts
    }

    override fun put(data: List<Post>?) {
       posts=data
    }


}