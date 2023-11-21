package com.example.instagram.home.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class HomeRepository(private val dataSourceFactory: HomeDataSourceFactory) {
    fun clearCache(){
        val localDataSource=dataSourceFactory.createLocalDataSource()
        localDataSource.putFeed(null)
    }

    fun fetchFeed( callBack: RequestCallBack<List<Post>>) {
        val localDataSource = dataSourceFactory.createLocalDataSource()
        // busca usuario na sessao
        val userAuth = localDataSource.fetchSession()
        val datasource = dataSourceFactory.createFromFeed()
        datasource.fetchFeed(userAuth.uuid, object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                localDataSource.putFeed(data)
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