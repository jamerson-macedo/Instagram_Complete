package com.example.instagram.camera

import android.net.Uri
import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView

interface Camera {
    interface Presenter:BasePresenter{
        fun createPost(photo: Uri,caption:String)

    }
    interface View:BaseView<Presenter>{
    fun showProgress(enabled:Boolean)
    fun displayRequestSuccess()
    fun displayRequestFailure(message:String)


    }
}