package com.example.instagram.profile.data

import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

object PostMemoryCache :ProfileCache<List<Post>>{
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