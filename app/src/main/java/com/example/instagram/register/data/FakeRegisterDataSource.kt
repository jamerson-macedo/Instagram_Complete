package com.example.instagram.register.data

import android.os.Handler
import android.os.Looper
import com.example.instagram.common.view.model.DataBase

class FakeRegisterDataSource : RegisterEmailDataSource {
    override fun create(email: String, callback: RegisterEmailCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val userAuth = DataBase.userAuths.firstOrNull { email == it.email }
            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("usuario ja cadastrado")
            }
            callback.onComplete()
        }, 2000)
    }
}