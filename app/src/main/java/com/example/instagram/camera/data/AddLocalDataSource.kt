package com.example.instagram.camera.data

import android.net.Uri
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth
import com.google.firebase.auth.FirebaseAuth
import java.lang.RuntimeException

class AddLocalDataSource:AddDataSource {
    override fun fetchSession(): String {
       return FirebaseAuth.getInstance().uid ?:throw RuntimeException("Usuario não encontrado")
    }
}