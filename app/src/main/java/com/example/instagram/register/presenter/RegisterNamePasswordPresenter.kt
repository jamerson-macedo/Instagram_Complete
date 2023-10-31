package com.example.instagram.register.presenter

import com.example.instagram.R
import com.example.instagram.register.RegisterNameAndPassword
import com.example.instagram.register.data.RegisterCallback
import com.example.instagram.register.data.RegisterRepository


class RegisterNamePasswordPresenter(
    private var view: RegisterNameAndPassword.View?,
    private val dataRepository: RegisterRepository
) :
    RegisterNameAndPassword.Presenter {
    override fun create(email: String, name: String, password: String, confirm: String) {
        val isnameValid = name.length > 3
        val isPasswordValid = password.length >= 8
        val confmPassword = password == confirm

        if (!isnameValid) {
            view?.nameDisplayFailure((R.string.name_invalid))

        } else {
            // nao mostra nada
            view?.nameDisplayFailure(null)
        }

        if (!confmPassword) {
            view?.passwordDisplayFailure(R.string.password_diferent)

        } else {
            if (!isPasswordValid) {
                view?.passwordDisplayFailure(R.string.invalidpassowrd)
            } else {
                view?.passwordDisplayFailure(null)
            }

        }
        if (isnameValid && isPasswordValid && confmPassword) {
            // tudo ok entao manda para o banco
            view?.showProgress(true)
            dataRepository.create(email, name, password, object : RegisterCallback {
                override fun onSuccess() {
                    view?.onCreateSucces(name)
                }

                override fun onFailure(messager: String) {
                    view?.onCreateFailure(messager)
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