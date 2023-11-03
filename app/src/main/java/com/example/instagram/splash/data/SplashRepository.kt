package com.example.instagram.splash.data

class SplashRepository(private val dataSource: SplashDataSource) {
    fun session(splashCallBack: SplashCallBack){
        dataSource.session(splashCallBack)

    }
}