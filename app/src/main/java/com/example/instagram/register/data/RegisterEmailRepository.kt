package com.example.instagram.register.data

class RegisterEmailRepository(private val dataSource: RegisterEmailDataSource) {
    fun create(email: String,callback: RegisterEmailCallback) {
// responsavel pelo que vai fazer com os dados
        // chama servidor ou banco de dados locais
        dataSource.create(email,callback)

    }

}