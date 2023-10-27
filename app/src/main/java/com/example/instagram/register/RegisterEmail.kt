package com.example.instagram.register

import androidx.annotation.StringRes
import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView


interface RegisterEmail {
    interface Presenter:BasePresenter{
        fun create(email:String)
    }
    interface View:BaseView<Presenter>{
        fun emailDisplayFailure(@StringRes emailError: Int?)
        fun onEmailFailure(message:String)
        fun showProgress(enabled: Boolean)
        fun goToNameAndPassword(email:String)

    }
}