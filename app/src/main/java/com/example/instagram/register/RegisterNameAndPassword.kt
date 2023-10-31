package com.example.instagram.register

import androidx.annotation.StringRes
import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView

interface RegisterNameAndPassword {
    interface Presenter : BasePresenter {
        fun create(email:String,name: String, password: String, confirm: String)
    }

    interface View : BaseView<Presenter> {
        fun nameDisplayFailure(@StringRes nameError: Int?)
        fun passwordDisplayFailure(@StringRes passError: Int?)
        fun onCreateSucces(name: String)
        fun onCreateFailure(message: String)
        fun showProgress(enabled: Boolean)


    }
}