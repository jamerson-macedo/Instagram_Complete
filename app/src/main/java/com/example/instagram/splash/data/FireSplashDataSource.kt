package com.example.instagram.splash.data

import com.google.firebase.auth.FirebaseAuth

class FireSplashDataSource:SplashDataSource {
    override fun session(callback: SplashCallBack) {
        if(FirebaseAuth.getInstance().uid!=null){
            callback.onSuccess()
        }else{
            callback.onFailure()
        }
    }
}