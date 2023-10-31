package com.example.instagram.register.presenter

import android.util.Patterns
import com.example.instagram.R
import com.example.instagram.register.RegisterEmail
import com.example.instagram.register.data.RegisterCallback
import com.example.instagram.register.data.RegisterRepository


class RegisterPresenter(private var view: RegisterEmail.View?, private val dataRepository: RegisterRepository) :
    RegisterEmail.Presenter {
    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()

        if (!isEmailValid) {
            view?.emailDisplayFailure(R.string.invalidEmail)

        } else {
            view?.emailDisplayFailure(null)
        }

        if (isEmailValid ) {
            // se der tudo certo entao
            // mostrar a progresss
            view?.showProgress(true)
            dataRepository.create(email, object : RegisterCallback {
                override fun onSuccess() {
                    // agora retorna a view a resposta
                    view?.goToNameAndPassword(email)
                }

                override fun onFailure(messager: String) {
                    view?.onEmailFailure(messager)
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