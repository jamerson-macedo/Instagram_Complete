package com.example.instagram.profile.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth

interface ProfileDataSource {
    fun fetchUserProfile(uuid: String, callBack: RequestCallBack<Pair<User,Boolean?>>)
    fun fetchUserPosts(uuid: String, callBack: RequestCallBack<List<Post>>)
    // SERVE PARA NAO IMPLEMENTAR OS METODOS
    fun fetchSession():String{throw UnsupportedOperationException()}
    fun logout(){throw UnsupportedOperationException()}
    fun putUser(response:Pair<User, Boolean?>?){throw UnsupportedOperationException()}
    fun putPosts(response:List<Post>?){throw UnsupportedOperationException()}
    fun followUser(uuid:String,isFollow:Boolean,callBack: RequestCallBack<Boolean>){throw UnsupportedOperationException()}
}