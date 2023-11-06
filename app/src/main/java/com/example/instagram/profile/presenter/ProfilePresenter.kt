package com.example.instagram.profile.presenter

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.profile.Profile
import com.example.instagram.profile.data.ProfileRepository
import java.lang.RuntimeException

class ProfilePresenter(private var view: Profile.View?, val repository: ProfileRepository) :
    Profile.Presenter {

    override fun fetchUserProfile() {
        view?.showProgress(true)
        val userId = DataBase.sessionAuth?.uuid ?: throw RuntimeException ("user not found")
        repository.fetchUserProfile(userId, object : RequestCallBack<UserAuth> {
            override fun onSuccess(data: UserAuth) {
               view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
               //
            }

        })

    }

    override fun fetchUserPost() {
        val userId = DataBase.sessionAuth?.uuid ?: throw RuntimeException ("user not found")
        repository.fetchUserPosts(userId,object :RequestCallBack<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                if(data.isEmpty()){
                    view?.displayEmptyPost()
                }
                view?.displayFullPost(data)

            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
               view?.showProgress(false)
            }

        })
    }

    override fun onDestroy() {
        view=null
    }
}