package com.example.instagram.profile.data

import android.util.Log
import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth
import com.google.firebase.auth.FirebaseAuth

class ProfileLocalDataSource(
    private val profileCache: Cache<Pair<User, Boolean?>>, private val postsCache: Cache<List<Post>>
) : ProfileDataSource {
    override fun fetchUserProfile(
        uuid: String,
        callBack: RequestCallBack<Pair<User, Boolean?>>
    ) {
        val userAuth = profileCache.get(uuid)
        if (userAuth != null) {
            callBack.onSuccess(userAuth)
        } else {
            callBack.onFailure("usuario nao encontrado")
        }
        callBack.onComplete()

    }

    override fun fetchUserPosts(uuid: String, callBack: RequestCallBack<List<Post>>) {
        val posts = postsCache.get(uuid)
        Log.i("userposts", posts?.size.toString())
        if (posts != null) {
            callBack.onSuccess(posts)
        } else {
            callBack.onFailure("posts nao exeistem")
        }
        callBack.onComplete()
    }

    override fun fetchSession(): String {
        return FirebaseAuth.getInstance().uid?: throw RuntimeException("Usuario nao encontrado")
    }

    override fun putUser(response: Pair<User, Boolean?>?) {
        profileCache.put(response)
    }

    override fun putPosts(response: List<Post>?) {
        postsCache.put(response)
    }
}