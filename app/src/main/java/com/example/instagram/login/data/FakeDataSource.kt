package com.example.instagram.login.data

import android.os.Handler
import android.os.Looper
import com.example.instagram.common.view.model.DataBase

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val userAuth = DataBase.userAuths.firstOrNull { email == it.email }
            when {
                userAuth == null -> {
                    callback.onFailure("usuario não encontrado")
                }
                userAuth.password != password -> {
                    callback.onFailure("senha incorreta")

                }
                else -> {
                    DataBase.sessionAuth = userAuth
                    callback.onSuccess()
                }
            }
            callback.onComplete()
        }, 2000)
    }
}