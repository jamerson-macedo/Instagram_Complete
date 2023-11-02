package com.example.instagram.register.presenter

import android.net.Uri
import com.example.instagram.register.RegisterPhoto
import com.example.instagram.register.data.RegisterCallback
import com.example.instagram.register.data.RegisterRepository

class RegisterPhotoPresenter(
    private var view: RegisterPhoto.View?,
    val repository: RegisterRepository
) : RegisterPhoto.Presenter {
    override fun updateUser(photoUri: Uri) {
        view?.showProgress(true)
        repository.updateUser(photoUri, object : RegisterCallback {
            override fun onSuccess() {
                view?.onUpdateSucces()
            }

            override fun onFailure(messager: String) {
                view?.onUpdateFailure(messager)
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