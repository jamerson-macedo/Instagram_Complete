package com.example.instagram.search

import com.example.instagram.common.view.base.BasePresenter
import com.example.instagram.common.view.base.BaseView
import com.example.instagram.common.view.model.UserAuth

interface Search {
    interface Presenter:BasePresenter{
    fun fetchUser(name:String)

    }
    interface View:BaseView<Presenter>{
        fun showProgress(enavled:Boolean)
      fun displayFullUsers(user:List<UserAuth>)
      fun displayEmpty()
    }
}