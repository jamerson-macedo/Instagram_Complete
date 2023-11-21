package com.example.instagram.post

import android.net.Uri
import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

interface Post {
    interface Presenter:BasePresenter{
        fun fetchPictures()
    }
    interface View:BaseView<Presenter>{
        fun showProgress(enable: Boolean)
        fun displayFullPictures(posts: List<Uri>)
        fun displayRequestFailure(message: String)
        fun displayEmptyPictures()
    }
}