package com.example.instagram.camera.data

import android.net.Uri
import com.example.instagram.common.view.base.RequestCallBack

class AddRepository(
    private val remotedataSource: FireAddDataSource,
    private val localDataSource: AddLocalDataSource
) {
    fun createPost(photo: Uri, caption:String, callBack: RequestCallBack<Boolean>) {
        val user=AddLocalDataSource().fetchSession()
        remotedataSource.createPost(user,photo,caption,object :RequestCallBack<Boolean>{
            override fun onSuccess(data: Boolean) {
                callBack.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callBack.onFailure(message)
            }

            override fun onComplete() {
                callBack.onComplete()
            }

        })
    }
}