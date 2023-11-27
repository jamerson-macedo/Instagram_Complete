package com.example.instagram.profile.data

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class FakeRemoteDataSource : ProfileDataSource {
    override fun fetchUserProfile(
        uuid: String,
        callBack: RequestCallBack<Pair<UserAuth, Boolean?>>
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val userAuth = DataBase.userAuths.firstOrNull { uuid == it.uuid }
            if (userAuth != null) {
                if (userAuth == DataBase.sessionAuth) {
                    callBack.onSuccess(Pair(userAuth, null))
                } else {
                    val followings = DataBase.followers[DataBase.sessionAuth!!.uuid]
                    val destUser = followings?.firstOrNull { it == uuid }
                    callBack.onSuccess(Pair(userAuth,destUser != null))
                }

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
            Log.i("postspos", posts.toString())
            callBack.onSuccess(posts?.toList() ?: emptyList())
            callBack.onComplete()
        }, 2000)
    }

    override fun followUser(uuid: String, isFollow: Boolean, callBack: RequestCallBack<Boolean>) {
        Handler(Looper.getMainLooper()).postDelayed({
            // pegando a tabela de seguidores do usuario atual
           var followers=DataBase.followers[DataBase.sessionAuth!!.uuid]
            if(followers==null){
                followers= mutableSetOf()
                DataBase.followers[DataBase.sessionAuth!!.uuid]=followers
            }
            if(isFollow){
                DataBase.followers[DataBase.sessionAuth!!.uuid]?.add(uuid)
            }else{
                DataBase.followers[DataBase.sessionAuth!!.uuid]!!.remove(uuid)
            }
            callBack.onSuccess(true)
            callBack.onComplete()
        }, 500)

    }

}