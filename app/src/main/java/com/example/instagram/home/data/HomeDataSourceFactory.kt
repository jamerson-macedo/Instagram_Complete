package com.example.instagram.home.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth


class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>
) {
    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }
    fun createFromFeed(): HomeDataSource {
        // verifica se tem usuario na cache
        if (feedCache.iscached()) {
            // se tiver busca em memoria
            return HomeLocalDataSource(feedCache)
        }
        // se nao busca do servidor
        return FireHomeDataSource()
    }

}