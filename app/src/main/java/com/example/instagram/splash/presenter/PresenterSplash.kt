package com.example.instagram.splash.presenter

import com.example.instagram.splash.Splash
import com.example.instagram.splash.data.SplashCallBack
import com.example.instagram.splash.data.SplashRepository

class PresenterSplash(var view: Splash.View?, val repository: SplashRepository): Splash.Presenter{
    override fun autenticated() {
        repository.session(object :SplashCallBack{
            override fun onSuccess() {
                view?.gotoMain()
            }

            override fun onFailure() {
                view?.gotoLogin()
            }
        })
    }

    override fun onDestroy() {
        view=null
    }

}