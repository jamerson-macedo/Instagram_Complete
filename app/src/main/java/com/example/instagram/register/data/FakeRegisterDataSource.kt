package com.example.instagram.register.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.Photo
import com.example.instagram.common.view.model.UserAuth
import java.util.UUID

class FakeRegisterDataSource : RegisterDataSource {
    override fun create(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val userAuth = DataBase.userAuths.firstOrNull { email == it.email }
            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("usuario ja cadastrado")
            }
            callback.onComplete()
        }, 2000)
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // se  o primeiro dado é igual ao que recebeu do banco
            // SELECT * FROM USER_AUTH WHERE EMAIL= ? LIMIT 1
            val userAuth = DataBase.userAuths.firstOrNull { email == it.email }
            if (userAuth != null) {
                callback.onFailure("usuario ja cadastrado")
            } else {

                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)
                val created = DataBase.userAuths.add(newUser)
                // se o usuario for criado entao
                if (created) {
                    // gravando no banco o novo usuario
                    DataBase.sessionAuth = newUser
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno no servidor")
                }
            }
            callback.onComplete()
        }, 2000)
    }

    override fun updateUser(uri: Uri, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // verifica o usuario atual
            val userAuth = DataBase.sessionAuth
            if (userAuth == null) {
                callback.onFailure("usuario não encontrado")
            } else {
                val newPhoto=Photo(uri,userAuth.uuid)
                // adicionando a nova foto
                val created=DataBase.photos.add(newPhoto)
                if(created){
                    callback.onSuccess()
                }else{
                    callback.onFailure("photo nao registrada")
                }

            }
            callback.onComplete()
        }, 2000)
    }
}