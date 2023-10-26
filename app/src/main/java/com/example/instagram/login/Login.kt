package com.example.instagram.login

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView

interface Login {
    // camada de presenter
    interface Presenter : BasePresenter {
        fun login(email: String, password: String)

    }

    // camada view
    interface View:BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun emailDisplayFailure(emailError: Int?)
        fun passWordDisplayFailure(passWordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized()


    }
}