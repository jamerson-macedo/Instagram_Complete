package com.example.instagram.profile

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

interface Profile {
    interface Presenter : BasePresenter{
        fun fetchUserProfile(userid:String?)
        fun fetchUserPost(userid:String?)
        fun clearCache()

    }
    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)
        fun displayUserProfile(userAuth: Pair<UserAuth, Boolean?>)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullPost(posts: List<Post>)
        fun displayFullFav(posts: List<Fav>)
    }
}