package com.example.instagram.splash.data

import com.example.instagram.common.view.model.DataBase

class FakeLocalDataSource :SplashDataSource{
    override fun session(callback: SplashCallBack) {
        if(DataBase.sessionAuth!=null){
            // usuario ja locagfo
            callback.onSuccess()
        }else{
            callback.onFailure()
        }
    }
}