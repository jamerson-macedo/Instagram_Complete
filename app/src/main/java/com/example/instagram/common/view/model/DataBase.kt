package com.example.instagram.common.view.model

import java.util.UUID

object DataBase {
    // hash set permite apenas um identificador unico
    val userAuths= hashSetOf<UserAuth>()
    // guardar se foi autenticado ou nao
    var sessionAuth:UserAuth?=null
    val photos= hashSetOf<Photo>()
    val posts=HashMap<String,Set<Post>>()
    // user1[post1,post2,post3]
    // user2[post1,post2,post3]
    val feeds=HashMap<String,Set<Post>>()
    // preciso anexar o usuario (string) e os posts dele
    init {
        userAuths.add(UserAuth(UUID.randomUUID().toString(),"jamerson","jamersonestilizado@gmail.com","12345678"))
        userAuths.add(UserAuth(UUID.randomUUID().toString(),"jamerson","estilizado@gmail.com","12345678"))
        // forcando inicio com usuario cadastrado
        sessionAuth=userAuths.first()
    }
}