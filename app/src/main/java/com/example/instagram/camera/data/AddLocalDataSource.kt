package com.example.instagram.camera.data

import android.net.Uri
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.UserAuth
import java.lang.RuntimeException

class AddLocalDataSource:AddDataSource {
    override fun fetchSession(): UserAuth {
       return DataBase.sessionAuth?:throw RuntimeException("Usuario não encontrado")
    }
}