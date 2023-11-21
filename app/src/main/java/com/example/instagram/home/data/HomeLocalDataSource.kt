package com.example.instagram.home.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class HomeLocalDataSource(private val feedCache: Cache<List<Post>>) : HomeDataSource {
    override fun fetchFeed(uuid: String, callBack: RequestCallBack<List<Post>>) {
        val posts=feedCache.get(uuid)
        if(posts!=null){
            callBack.onSuccess(posts)
        }else{
            callBack.onFailure("sem posts ainda")
        }
        callBack.onComplete()
    }

    override fun fetchSession(): UserAuth {
        return DataBase.sessionAuth ?: throw RuntimeException("Usuario nao encontrado")
    }

    override fun putFeed(response: List<Post>?) {
        feedCache.put(response)
    }


}