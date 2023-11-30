package com.example.instagram.common.view.base

import android.content.Context
import com.example.instagram.camera.data.AddFakeRemoteDataSource
import com.example.instagram.camera.data.AddLocalDataSource
import com.example.instagram.camera.data.AddRepository
import com.example.instagram.home.data.FeedMemoryCache
import com.example.instagram.home.data.HomeDataSourceFactory
import com.example.instagram.home.data.HomeRepository
import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.post.Post
import com.example.instagram.post.data.PostDataSource
import com.example.instagram.post.data.PostLocalDataSource
import com.example.instagram.post.data.PostRepository
import com.example.instagram.profile.data.PostMemoryCache
import com.example.instagram.profile.data.ProfileDataSourceFactory
import com.example.instagram.profile.data.ProfileMemoryCache
import com.example.instagram.profile.data.ProfileRepository
import com.example.instagram.register.data.FakeRegisterDataSource
import com.example.instagram.register.data.FireRegisterDataSource
import com.example.instagram.register.data.RegisterRepository
import com.example.instagram.search.data.SearchFakeRemoteDataSource
import com.example.instagram.search.data.SearchRepository
import com.example.instagram.splash.data.FakeLocalDataSource
import com.example.instagram.splash.data.SplashRepository

object DependencyInjector {
    // serve para obter a instacia sem precisar colocar na activity
    fun loginRepository(): LoginRepository {
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository {
        return RegisterRepository(FireRegisterDataSource())
    }

    fun SplashRepository(): SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache, PostMemoryCache))
    }

    fun homeRepository(): HomeRepository {
        return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
    }
    fun addRepository(): AddRepository {
        return AddRepository(AddFakeRemoteDataSource(), AddLocalDataSource())
    }
    fun postRepository(context: Context):PostRepository{
        return PostRepository(PostLocalDataSource(context))
    }
    fun searchRepository():SearchRepository{
        return SearchRepository(SearchFakeRemoteDataSource())
    }


}