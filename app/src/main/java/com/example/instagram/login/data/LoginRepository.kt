package com.example.instagram.login.data

import com.example.instagram.login.Login

class LoginRepository(private val dataSource: LoginDataSource) {
    fun login(email: String, password: String,callback: LoginCallback) {
// responsavel pelo que vai fazer com os dados
        // chama servidor ou banco de dados locais
        dataSource.login(email,password,callback)

    }

}