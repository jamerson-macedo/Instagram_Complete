package com.example.instagram.profile.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class FakeRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(uuid: String, callBack: RequestCallBack<UserAuth>) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val userAuth = DataBase.userAuths.firstOrNull { uuid == it.uuid }
            if (userAuth != null) {
                callBack.onSuccess(userAuth)
            } else {
                callBack.onFailure("usuario nao encontrado")
            }
            callBack.onComplete()
        }, 2000)
    }

    override fun fetchUserPosts(uuid: String, callBack: RequestCallBack<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val posts = DataBase.posts[uuid]
            Log.i("posts",posts.toString())
            callBack.onSuccess(posts?.toList() ?: emptyList())
            callBack.onComplete()
        }, 2000)
    }


}