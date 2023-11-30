package com.example.instagram.profile

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth

interface Profile {
    interface Presenter : BasePresenter{
        fun fetchUserProfile(userid:String?)
        fun fetchUserPost(userid:String?)
        fun clearCache()
        fun followUser(uuid:String?,follow:Boolean)

    }
    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)
        fun displayUserProfile(user: Pair<User, Boolean?>)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullPost(posts: List<Post>)
        fun displayFullFav(posts: List<Fav>)
    }
}