package com.example.instagram.register.data

class RegisterRepository(private val dataSource: RegisterDataSource) {
    fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        //responsavel pelo que vai fazer com os dados
        // chama servidor ou banco de dados locais
        dataSource.create(email,name,password, callback)

    }
    fun create(email: String, callback: RegisterCallback) {
        //responsavel pelo que vai fazer com os dados
        // chama servidor ou banco de dados locais
        dataSource.create(email, callback)

    }

}