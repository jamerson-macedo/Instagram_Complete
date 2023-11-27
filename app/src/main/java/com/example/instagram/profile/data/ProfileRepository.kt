package com.example.instagram.profile.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class ProfileRepository(private val dataSourceFactory: ProfileDataSourceFactory) {
    fun clearCache() {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        localDataSource.putPosts(null)
    }

    fun fetchUserProfile(userid: String?, callBack: RequestCallBack<Pair<UserAuth, Boolean?>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        // busca usuario na sessao

        val userAuth = userid ?: localDataSource.fetchSession().uuid
        // ou o local ou o remote
        val datasource = dataSourceFactory.createFromUser(userid)
        datasource.fetchUserProfile(userAuth, object : RequestCallBack<Pair<UserAuth, Boolean?>>{
            override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
                if(userid==null){
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
        val userAuth = userid ?: localDataSource.fetchSession().uuid

        val datasource = dataSourceFactory.createFromPosts(userid)
        datasource.fetchUserPosts(userAuth, object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if(userid==null){
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
}