package com.example.instagram.common.view.model

import android.net.Uri
import android.os.Parcel
import android.os.Parcelable

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Post(val UUID: String, val uri: Uri, val caption: String, val timestamp: Long) :Parcelable{}
