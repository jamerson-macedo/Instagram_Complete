package com.example.instagram.home.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post

class HomeFakeRemoteDataSource : HomeDataSource {
    override fun fetchFeed(uuid: String, callBack: RequestCallBack<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val feeds = DataBase.feeds[uuid]
            Log.i("posts",feeds.toString())
            callBack.onSuccess(feeds?.toList() ?: emptyList())
            callBack.onComplete()
        }, 2000)
    }

}