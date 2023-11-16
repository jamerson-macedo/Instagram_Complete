package com.example.instagram.profile.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

interface ProfileDataSource {
    fun fetchUserProfile(uuid: String, callBack: RequestCallBack<UserAuth>)
    fun fetchUserPosts(uuid: String, callBack: RequestCallBack<List<Post>>)
}