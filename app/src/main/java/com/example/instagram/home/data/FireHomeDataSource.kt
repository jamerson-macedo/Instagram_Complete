package com.example.instagram.home.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class FireHomeDataSource : HomeDataSource {
    override fun fetchFeed(uuid: String, callBack: RequestCallBack<List<Post>>) {
        val uid = FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuario não encontrado")
        FirebaseFirestore.getInstance().collection("/feeds").document(uid).collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING).get()
            .addOnSuccessListener {res->
                val feed= mutableListOf<Post>()
                val documents=res.documents
                for(document in documents){
                   val post= document.toObject(Post::class.java)
                    post?.let {
                        feed.add(it)
                    }
                }
                callBack.onSuccess(feed)

            }.addOnFailureListener {
                callBack.onFailure(it.message ?: "erro no servidor")

            }.addOnCompleteListener {
                callBack.onComplete()
            }
    }
}