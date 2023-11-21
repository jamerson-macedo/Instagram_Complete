package com.example.instagram.profile.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth


class ProfileDataSourceFactory(
    private val profileCache: Cache<UserAuth>,
    private val postCache: Cache<List<Post>>
) {
    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postCache)
    }

    fun createFromUser(): ProfileDataSource {
        // verifica se tem usuario na cache
        if (profileCache.iscached()) {
            // se tiver busca em memoria
            return ProfileLocalDataSource(profileCache, postCache)
        }
        // se nao busca do servidor
        return FakeRemoteDataSource()
    }
    fun createFromPosts(): ProfileDataSource {
        // verifica se tem usuario na cache
        if (postCache.iscached()) {
            // se tiver busca em memoria
            return ProfileLocalDataSource(profileCache, postCache)
        }
        // se nao busca do servidor
        return FakeRemoteDataSource()
    }

}