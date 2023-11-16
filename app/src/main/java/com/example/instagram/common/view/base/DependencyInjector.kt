package com.example.instagram.common.view.base

import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.profile.data.FakeRemoteDataSource
import com.example.instagram.profile.data.PostMemoryCache
import com.example.instagram.profile.data.ProfileDataSource
import com.example.instagram.profile.data.ProfileDataSourceFactory
import com.example.instagram.profile.data.ProfileMemoryCache
import com.example.instagram.profile.data.ProfileRepository
import com.example.instagram.register.data.FakeRegisterDataSource
import com.example.instagram.register.data.RegisterRepository
import com.example.instagram.splash.data.FakeLocalDataSource
import com.example.instagram.splash.data.SplashRepository

object DependencyInjector {
    // serve para obter a instacia sem precisar colocar na activity
     fun loginRepository():LoginRepository {
         return LoginRepository(FakeDataSource())
     }
    fun registerEmailRepository():RegisterRepository {
        return RegisterRepository(FakeRegisterDataSource())
    }
    fun SplashRepository():SplashRepository {
        return SplashRepository(FakeLocalDataSource())
    }
    fun profileRepository():ProfileRepository {
        return ProfileRepository(ProfileDataSourceFactory(ProfileMemoryCache,PostMemoryCache))
    }

}