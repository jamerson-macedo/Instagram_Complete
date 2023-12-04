package com.example.instagram.profile.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {
    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
        localDataSource.putUser(null)
    }

    fun fetchUserProfile(userid: String?, callBack: RequestCallBack<Pair<User, Boolean?>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        // busca usuario na sessao

        val userAuth = userid ?: localDataSource.fetchSession()
        // ou o local ou o remote
        val datasource = dataSourceFactory.createFromUser(userid)
        datasource.fetchUserProfile(userAuth, object : RequestCallBack<Pair<User, Boolean?>> {
            override fun onSuccess(data: Pair<User, Boolean?>) {
                if (userid == null) {
                    localDataSource.putUser(data)
                }

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

    fun fetchUserPosts(userid: String?, callBack: RequestCallBack<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        // busca usuario na sessao
        val userAuth = userid ?: localDataSource.fetchSession()

        val datasource = dataSourceFactory.createFromPosts(userid)
        datasource.fetchUserPosts(userAuth, object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (userid == null) {
                    localDataSource.putPosts(data)
                }

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

    fun followUser(uuid: String?, follow: Boolean, callBack: RequestCallBack<Boolean>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        val userAuth = uuid ?: localDataSource.fetchSession()
        val datasource = dataSourceFactory.createRemoteDataSource()
        datasource.followUser(userAuth, follow, object : RequestCallBack<Boolean> {
            override fun onSuccess(data: Boolean) {
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