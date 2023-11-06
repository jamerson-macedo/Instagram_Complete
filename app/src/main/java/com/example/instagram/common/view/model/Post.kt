package com.example.instagram.common.view.model

import android.net.Uri

data class Post(val UUID: String, val uri: Uri, val caption: String, val timestamp: Long) {
}