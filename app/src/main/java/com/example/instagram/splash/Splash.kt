package com.example.instagram.splash

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView

interface Splash {
    interface Presenter:BasePresenter{
        fun autenticated()
    }
    interface View:BaseView<Presenter>{
        fun gotoMain()
        fun gotoLogin()
    }

}