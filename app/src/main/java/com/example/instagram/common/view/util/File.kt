package com.example.instagram.common.view.util

import android.app.Activity
import com.example.instagram.R
import java.io.File
import java.text.SimpleDateFormat
import java.util.Locale

object File {
    const val FILE_NAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
    fun generateFile(activity: Activity): File {
        // pega o primeiro ou nulo que encontrar das midias
        val medias = activity.externalMediaDirs.firstOrNull()?.let {
            File(it, activity.getString(R.string.app_name)).apply {
                mkdir()
            }
        }
        val outputDir = if (medias != null && medias.exists()) medias else activity.filesDir
        return File(
            outputDir,
            SimpleDateFormat(
                FILE_NAME_FORMAT,
                Locale.US
            ).format(System.currentTimeMillis()) + ".jpg"
        )

    }
}