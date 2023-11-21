package com.example.instagram.post.data

import android.net.Uri

interface PostDataSource {
    suspend fun fetchPictures():List<Uri>
}