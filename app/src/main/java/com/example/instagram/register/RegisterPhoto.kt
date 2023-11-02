package com.example.instagram.register

import android.net.Uri
import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView

interface RegisterPhoto {
    interface Presenter : BasePresenter {
        fun updateUser(photoUri:Uri)
    }

    interface View : BaseView<Presenter> {

        fun onUpdateSucces()
        fun onUpdateFailure(message: String)
        fun showProgress(enabled: Boolean)


    }
}