package com.example.instagram.post.presenter

import android.net.Uri
import com.example.instagram.post.Post
import com.example.instagram.post.data.PostRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

class PostPresenter(private var view: Post.View?, val repository: PostRepository) : Post.Presenter,
    CoroutineScope {
    private var uri:Uri?=null
    // por estar dentro de uma suspend fun tem chamar uma corrotina
    private val job = Job()

    // criando um job paralelo
    override val coroutineContext: CoroutineContext = job + Dispatchers.IO

    override fun fetchPictures() {
        view?.showProgress(true)
        launch {
            val pictures = repository.fetchPictures()
            withContext(Dispatchers.Main) {
                if (pictures.isEmpty()) {
                    view?.displayEmptyPictures()
                } else {
                    view?.displayFullPictures(pictures)
                }
                view?.showProgress(false)
            }

        }


    }

    override fun getselectedUri(): Uri? {
       return uri
    }

    override fun selectedUri(uri: Uri) {
        this.uri=uri
    }

    override fun onDestroy() {
        job.cancel()
        view=null

    }
}