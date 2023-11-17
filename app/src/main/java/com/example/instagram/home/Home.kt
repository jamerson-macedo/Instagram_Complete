package com.example.instagram.home

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post

interface Home {
    interface Presenter:BasePresenter{
        fun fetchPost()

    }
    interface View:BaseView<Presenter>{
        fun showProgress(enable: Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullPost(posts: List<Post>)
        fun displayFullFav(posts: List<Fav>)


    }
}