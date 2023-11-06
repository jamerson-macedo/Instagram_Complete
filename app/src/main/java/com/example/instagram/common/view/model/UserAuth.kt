package com.example.instagram.common.view.model


data class UserAuth(
    val uuid: String,
    val name:String,
    val email: String,
    val password: String,
    val postCount:Int=0,
    val FollowingCount:Int=0,
    val FollowersgCount:Int=0
){

}