package com.example.instagram.camera.presenter

import android.net.Uri
import com.example.instagram.camera.Camera
import com.example.instagram.camera.data.AddRepository
import com.example.instagram.common.view.base.RequestCallBack

class AddPresenter(var view: Camera.View?=null, val repository: AddRepository) : Camera.Presenter {
    override fun createPost(photo: Uri, caption: String) {
        view?.showProgress(true)
        repository.createPost(photo,caption,object :RequestCallBack<Boolean>{
            override fun onSuccess(data: Boolean) {
                if(data){
                    view?.displayRequestSuccess()
                }else{
                    view?.displayRequestFailure("erro interno")
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

    override fun onDestroy() {
        view = null
    }
}