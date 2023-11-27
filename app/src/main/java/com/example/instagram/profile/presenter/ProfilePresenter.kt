package com.example.instagram.profile.presenter

import android.util.Log
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.profile.Profile
import com.example.instagram.profile.data.ProfileRepository

class ProfilePresenter(private var view: Profile.View?, val repository: ProfileRepository) : Profile.Presenter {
    override fun fetchUserProfile(userid:String?) {
        view?.showProgress(true)
        repository.fetchUserProfile(userid, object : RequestCallBack<Pair<UserAuth, Boolean?>> {
            override fun onSuccess(data: Pair<UserAuth, Boolean?>) {
                // passando o estado da view
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


    override fun fetchUserPost(userid:String?) {
        repository.fetchUserPosts( userid,object : RequestCallBack<List<Post>> {
            override fun onSuccess(data: List<Post>) {
                Log.i("datauser",data.toString())
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

    override fun clearCache() {
        repository.clearCache()
    }

    override fun followUser(uuid: String?, follow: Boolean) {
        repository.followUser(uuid,follow, object :RequestCallBack<Boolean>{
            override fun onSuccess(data: Boolean) {
            }

            override fun onFailure(message: String) {

            }

            override fun onComplete() {

            }

        })
    }


    override fun onDestroy() {
        view = null
    }
}
/*
      override fun susbscribe(state: Profile.State?) {
          posts = state?.fetchUserPost()
          if (posts != null) {
              if (posts!!.isEmpty()) {
                  view?.displayEmptyPost()
              } else {
                  view?.displayFullPost(posts!!)
              }
          } else {
              val userId = DataBase.sessionAuth?.uuid ?: throw RuntimeException("user not found")
              repository.fetchUserPosts(userId, object : RequestCallBack<List<Post>> {
                  override fun onSuccess(data: List<Post>) {
                      posts = data
                      if (data.isEmpty()) {
                          view?.displayEmptyPost()
                      } else {
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
      */