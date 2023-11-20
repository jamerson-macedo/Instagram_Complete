package com.example.instagram.common.view.model

import java.util.UUID

object DataBase {
    // hash set permite apenas um identificador unico
    val userAuths = hashSetOf<UserAuth>()

    // guardar se foi autenticado ou nao
    var sessionAuth: UserAuth? = null
    val photos = hashSetOf<Photo>()
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
                "12345678"

        )
        val userb = UserAuth(
                UUID.randomUUID().toString(),
                "jamerson",
                "estilizado@gmail.com",
                "12345678"
            )

        userAuths.add(userb)
        userAuths.add(usera)


        followers[usera.uuid]= hashSetOf()
        posts[usera.uuid]= hashSetOf()
        feeds[usera.uuid]= hashSetOf()

        followers[userb.uuid]= hashSetOf()
        posts[userb.uuid]= hashSetOf()
        feeds[userb.uuid]= hashSetOf()
        // forcando inicio com usuario cadastrado
        sessionAuth = userAuths.first()
    }
}