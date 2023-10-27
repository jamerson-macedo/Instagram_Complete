package com.example.instagram.common.view.model

import java.util.UUID

object DataBase {
    // hash set permite apenas um identificador unico
    val userAuths= hashSetOf<UserAuth>()
    // guardar se foi autenticado ou nao
    var sessionAuth:UserAuth?=null
    init {
        userAuths.add(UserAuth(UUID.randomUUID().toString(),"jamersonestilizado@gmail.com","12345678"))
        userAuths.add(UserAuth(UUID.randomUUID().toString(),"estilizado@gmail.com","12345678"))
    }
}