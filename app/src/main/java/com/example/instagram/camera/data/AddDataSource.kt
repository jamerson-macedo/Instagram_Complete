package com.example.instagram.camera.data

import android.net.Uri
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.UserAuth

interface AddDataSource {
    // retorna boolean caso seja gravado
    fun createPost(uuid: String, photo:Uri,caption:String, callBack:RequestCallBack<Boolean>) { throw UnsupportedOperationException()}
    fun fetchSession():String{ throw UnsupportedOperationException()}
}