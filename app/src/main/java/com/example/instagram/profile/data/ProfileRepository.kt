package com.example.instagram.profile.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class ProfileRepository(private val dataSource: ProfileDataSource) {
    fun fetchUserProfile(uuid: String, callBack: RequestCallBack<UserAuth>) {
        dataSource.fetchUserProfile(uuid, callBack)
    }

    fun fetchUserPosts(uuid: String, callBack: RequestCallBack<List<Post>>) {
        dataSource.fetchUserPosts(uuid, callBack)
    }
}