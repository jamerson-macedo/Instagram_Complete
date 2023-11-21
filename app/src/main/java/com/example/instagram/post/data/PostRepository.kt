package com.example.instagram.post.data

import android.net.Uri

class PostRepository(val postDataSource: PostDataSource) {
    suspend fun fetchPictures()= postDataSource.fetchPictures()

}