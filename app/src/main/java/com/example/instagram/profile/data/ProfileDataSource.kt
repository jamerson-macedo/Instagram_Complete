package com.example.instagram.profile.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

interface ProfileDataSource {
    fun fetchUserProfile(uuid: String, callBack: RequestCallBack<Pair<UserAuth,Boolean?>>)
    fun fetchUserPosts(uuid: String, callBack: RequestCallBack<List<Post>>)
    // SERVE PARA NAO IMPLEMENTAR OS METODOS
    fun fetchSession():UserAuth{throw UnsupportedOperationException()}
    fun putUser(response:Pair<UserAuth, Boolean?>){throw UnsupportedOperationException()}
    fun putPosts(response:List<Post>?){throw UnsupportedOperationException()}
    fun followUser(uuid:String,isFollow:Boolean,callBack: RequestCallBack<Boolean>){throw UnsupportedOperationException()}
}