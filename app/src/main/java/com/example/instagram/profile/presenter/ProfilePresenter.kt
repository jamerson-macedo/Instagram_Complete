package com.example.instagram.profile.presenter

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.profile.Profile
import com.example.instagram.profile.data.ProfileRepository

class ProfilePresenter(private var view: Profile.View?, val repository: ProfileRepository) :
    Profile.Presenter {
    var users: UserAuth? = null
    var posts: List<Post>? = null

    /* override fun fetchUserProfile() {
         view?.showProgress(true)
         val userId = DataBase.sessionAuth?.uuid ?: throw RuntimeException("user not found")
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
     */

    /* override fun fetchUserPost() {
         val userId = DataBase.sessionAuth?.uuid ?: throw RuntimeException("user not found")
         repository.fetchUserPosts(userId, object : RequestCallBack<List<Post>> {
             override fun onSuccess(data: List<Post>) {
                 if (data.isEmpty()) {
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
     */

    override fun subscribe(state: Profile.State?) {
        // [ega a instancia se existe
        posts = state?.fetchUserPost()
        if (posts != null) {
            if (posts!!.isEmpty()) {
                view?.displayEmptyPost()
            }else{
                view?.displayFullPost(posts!!)
            }

        } else {
            val userId = DataBase.sessionAuth?.uuid ?: throw RuntimeException("user not found")
            repository.fetchUserPosts(userId, object : RequestCallBack<List<Post>> {
                override fun onSuccess(data: List<Post>) {
                    posts = data
                    if (data.isEmpty()) {
                        view?.displayEmptyPost()
                    }else{
                        view?.displayFullPost(data)
                    }
                }

                override fun onFailure(message: String) {
                    view?.displayRequestFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }
        users = state?.fetchUserProfile()
        if (users != null) {
            view?.displayUserProfile(users!!)
        } else {
            view?.showProgress(true)
            val userId = DataBase.sessionAuth?.uuid ?: throw RuntimeException("user not found")
            repository.fetchUserProfile(userId, object : RequestCallBack<UserAuth> {
                override fun onSuccess(data: UserAuth) {
                    users = data
                    view?.displayUserProfile(data)
                }

                override fun onFailure(message: String) {
                    view?.displayRequestFailure(message)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }

            })
        }


    }

    override fun getState(): Profile.State {
        return ProfileState(user = users, posts = posts)
    }

    override fun onDestroy() {
        view = null
    }
}