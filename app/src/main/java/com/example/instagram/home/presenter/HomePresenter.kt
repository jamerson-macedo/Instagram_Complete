package com.example.instagram.home.presenter

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.home.Home
import com.example.instagram.home.data.HomeRepository

class HomePresenter(private var view: Home.View?, val repository: HomeRepository) : Home.Presenter {
    override fun fetchPost() {
        view?.showProgress(true)
        repository.fetchFeed(object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                if (data.isEmpty()) {
                    view?.displayEmptyPost()
                } else {
                    view?.displayFullPost(data)
                }

            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure("nada encontrado")
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