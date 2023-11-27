package com.example.instagram.profile.data

import com.example.instagram.common.view.base.Cache
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth


class ProfileDataSourceFactory(
    private val profileCache: Cache<Pair<UserAuth, Boolean?>>,
    private val postCache: Cache<List<Post>>
) {
    fun createLocalDataSource(): ProfileDataSource {
        return ProfileLocalDataSource(profileCache, postCache)
    }

    fun createFromUser(userid:String?): ProfileDataSource {
        // verifica se tem usuario na cache
        if (userid!=null ) {
            return FakeRemoteDataSource()
            // se tiver busca em memoria
        }
        if(profileCache.iscached()){
            return ProfileLocalDataSource(profileCache, postCache)
        }
        return FakeRemoteDataSource()

    }
    fun createFromPosts(userid:String?): ProfileDataSource {
        if (userid!=null ) {
            return FakeRemoteDataSource()
            // se tiver busca em memoria
        }
        // verifica se tem usuario na cache
        if (postCache.iscached()) {
            // se tiver busca em memoria
            return ProfileLocalDataSource(profileCache, postCache)
        }
        // se nao busca do servidor
        return FakeRemoteDataSource()
    }

}