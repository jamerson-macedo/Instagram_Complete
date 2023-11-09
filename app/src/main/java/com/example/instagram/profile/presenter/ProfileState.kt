package com.example.instagram.profile.presenter

import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.profile.Profile

class ProfileState(private val posts:List<Post>?,private val user:UserAuth?): Profile.State {
    override fun fetchUserProfile(): UserAuth? {
        return user
    }

    override fun fetchUserPost(): List<Post>? {
        return posts

    }
}