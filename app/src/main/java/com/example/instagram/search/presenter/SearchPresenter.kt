package com.example.instagram.search.presenter

import android.util.Log
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.profile.Profile
import com.example.instagram.profile.data.ProfileRepository
import com.example.instagram.search.Search
import com.example.instagram.search.data.SearchRepository

class SearchPresenter(private var view: Search.View?, val repository: SearchRepository) : Search.Presenter {
    override fun fetchUser(name: String) {
        view?.showProgress(true)
        repository.fetchUsers(name,object :RequestCallBack<List<UserAuth>>{
            override fun onSuccess(data: List<UserAuth>) {
                if (data.isEmpty()) {
                    view?.displayEmpty()
                }else{
                    view?.displayFullUsers(data)
                }

            }
            override fun onFailure(message: String) {
                view?.displayEmpty()
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }


    override fun onDestroy() {
        view = null
    }
}
