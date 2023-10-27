package com.example.instagram.login.data

import android.os.Handler
import android.os.Looper

class FakeDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (email == "jamersonestilizado@gmail.com" && password == "12345678") {
                callback.onSuccess()
            } else {
                callback.onFailure("problema nos dados")
            }
            callback.onComplete()
        }, 2000)
    }
}