package com.example.instagram.camera.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import java.util.UUID

class AddFakeRemoteDataSource : AddDataSource {
    override fun createPost(
        uuid: String,
        photo: Uri,
        caption: String,
        callBack: RequestCallBack<Boolean>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            var posts = DataBase.posts[uuid]
            if (posts == null) {
                // criando um postvazio
                posts = mutableSetOf()
                DataBase.posts[uuid] = posts
            }
            // remover essa classe
            val post = Post(
                UUID.randomUUID().toString(),
                photourl =  null,
                caption,
                System.currentTimeMillis(),
                null
            )
            posts.add(post)
            var followers = DataBase.followers[uuid]
            if (followers == null) {
                // criando um postvazio
                followers = mutableSetOf()
                DataBase.followers[uuid] = followers
            } else {
                for (follower in followers) {
                    DataBase.feeds[follower]?.add(post)
                }
                DataBase.feeds[uuid]?.add(post)
            }
            callBack.onSuccess(true)
        }, 1000)
    }
}