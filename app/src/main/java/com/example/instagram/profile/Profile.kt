package com.example.instagram.profile

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

interface Profile {
    interface Presenter : StateFulPresenter<State> {

    }
    interface StateFulPresenter<S:State>:BasePresenter{
        fun subscribe(state:S?)
        fun getState():S
    }
    interface State{
        fun fetchUserProfile():UserAuth?
        fun fetchUserPost():List<Post>?
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enable: Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message:String)
        fun displayEmptyPost()
        fun displayFullPost(posts:List<Post>)
    }
}