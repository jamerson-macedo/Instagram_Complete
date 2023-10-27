package com.example.instagram.login.presenter

import android.util.Patterns
import com.example.instagram.R
import com.example.instagram.login.Login
import com.example.instagram.login.data.LoginCallback
import com.example.instagram.login.data.LoginRepository

class LoginPresenter(private var view: Login.View?, private val dataRepository: LoginRepository) :
    Login.Presenter {
    override fun login(email: String, password: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val isPasswordValid = password.length >= 8

        if (!isEmailValid) {
            view?.emailDisplayFailure(R.string.invalidEmail)

        } else {
            view?.emailDisplayFailure(null)
        }
        if (!isPasswordValid) {
            view?.passWordDisplayFailure(R.string.invalidpassowrd)
        } else {
            view?.passWordDisplayFailure(null)

        }
        if (isPasswordValid && isEmailValid) {
            // se der tudo certo entao
            // mostrar a progresss
            view?.showProgress(true)
            dataRepository.login(email, password, object : LoginCallback {
                override fun onSuccess() {
                    // agora retorna a view a resposta
                    view?.onUserAuthenticated()
                }

                override fun onFailure(messager: String) {
                    view?.onUserUnauthorized(messager)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })

        }
    }

    override fun onDestroy() {
        // removendo a referencia da view
        view = null
    }
}