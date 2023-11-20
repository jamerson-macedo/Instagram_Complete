package com.example.instagram.profile.data

import android.util.Log
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {
    fun fetchUserProfile(callBack: RequestCallBack<UserAuth>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        // busca usuario na sessao
        val userAuth = localDataSource.fetchSession()
        // ou o local ou o remote
        val datasource = dataSourceFactory.createFromUser()
        datasource.fetchUserProfile(userAuth.uuid, object : RequestCallBack<UserAuth> {
            override fun onSuccess(data: UserAuth) {
               localDataSource.putUser(data)
               callBack.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callBack.onFailure(message)
            }

            override fun onComplete() {
                callBack.onComplete()
            }
        })
    }

    fun fetchUserPosts( callBack: RequestCallBack<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        // busca usuario na sessao
        val userAuth = localDataSource.fetchSession()
        val datasource = dataSourceFactory.createFromPosts()
        datasource.fetchUserPosts(userAuth.uuid, object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putPosts(data)
                callBack.onSuccess(data)
            }

            override fun onFailure(message: String) {
               callBack.onFailure(message)
            }

            override fun onComplete() {
              callBack.onComplete()
            }
        })
    }
}