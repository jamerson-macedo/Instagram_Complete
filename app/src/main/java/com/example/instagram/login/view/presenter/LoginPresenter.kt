package com.example.instagram.login.view.presenter

import android.util.Patterns
import com.example.instagram.R
import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.login.Login

class LoginPresenter(private var view: Login.View?) : Login.Presenter {
    override fun login(email: String, password: String) {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            view?.emailDisplayFailure(R.string.invalidEmail)

        } else {
            view?.emailDisplayFailure(null)
        }
        if (password.length < 8) {
            view?.passWordDisplayFailure(R.string.invalidpassowrd)
        } else {
            view?.passWordDisplayFailure(null)

        }
    }

    override fun onDestroy() {
        // removendo a referencia da view
        view=null
    }
}