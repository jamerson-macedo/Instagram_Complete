package com.example.instagram.common.view.model

import java.util.UUID

object DataBase {
    // hash set permite apenas um identificador unico
    val userAuths = mutableListOf<UserAuth>()

    // guardar se foi autenticado ou nao
    var sessionAuth: UserAuth? = null
    val posts = HashMap<String, MutableSet<Post>>()

    // user1[post1,post2,post3]
    // user2[post1,post2,post3]
    val feeds = HashMap<String, MutableSet<Post>>()

    // preciso anexar o usuario (string) e os posts dele
    val followers = HashMap<String, MutableSet<String>>()

    // 1 usuario vai ter n seguidores
    init {
        val usera = UserAuth(
                UUID.randomUUID().toString(),
                "jamerson",
                "jamersonestilizado@gmail.com",
                "12345678",null

        )
//        val userb = UserAuth(
//                UUID.randomUUID().toString(),
//                "jamerson",
//                "estilizado@gmail.com",
//                "12345678",null
//            )
//
//
       userAuths.add(usera)
//        userAuths.add(usera)
//
//
//        followers[usera.uuid]= hashSetOf()
//        posts[usera.uuid]= hashSetOf()
//        feeds[usera.uuid]= hashSetOf()
//
//        followers[userb.uuid]= hashSetOf()
//        posts[userb.uuid]= hashSetOf()
//        feeds[userb.uuid]= hashSetOf()
//
//        for (i in 0..30){
//            val user=UserAuth(UUID.randomUUID().toString(),"user$i","user$1@gmail.com","123123123",null)
//            userAuths.add(user)
//        }


        // forcando inicio com usuario cadastrado
        //sessionAuth = userAuths.first()
       // followers[sessionAuth!!.uuid]?.add(userAuths[2].uuid)
    }
}