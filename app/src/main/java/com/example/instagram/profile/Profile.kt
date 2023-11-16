package com.example.instagram.profile

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

interface Profile {
    interface Presenter : StateFullPresenter<State>{

    }
    interface StateFullPresenter<S:State>:BasePresenter{
        fun susbscribe(state:S?)
        fun getState():S
    }

    interface State {
        fun fetchUserProfile(): UserAuth?
        fun fetchUserPost():List<Post>?
    }


    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullPost(posts: List<Post>)
        fun displayFullFav(posts: List<Fav>)
    }
}