package com.example.instagram.profile.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query

class FireProfileDataSource : ProfileDataSource {
    override fun fetchUserProfile(
        uuid: String,
        callBack: RequestCallBack<Pair<User, Boolean?>>
    ) {
        FirebaseFirestore.getInstance().collection("/users").document(uuid).get()
            .addOnSuccessListener { res ->
                val user = res.toObject(User::class.java)
                when (user) {
                    null -> {
                        callBack.onFailure("falha ao Converter")
                    }

                    else -> {
                        if (user.uuid == FirebaseAuth.getInstance().uid) {
                            callBack.onSuccess(Pair(user, null))
                        } else {
                            FirebaseFirestore.getInstance().collection("/followers")
                                .document(uuid).get().addOnSuccessListener { response ->
                                    if (!response.exists()) {
                                        callBack.onSuccess(Pair(user, false))
                                    } else {
                                        val list = response.get("followers") as List<String>
                                        callBack.onSuccess(
                                            Pair(
                                                user,
                                                list.contains(FirebaseAuth.getInstance().uid)
                                            )
                                        )
                                    }

                                }.addOnFailureListener {
                                    callBack.onFailure(it.message ?: "falha")

                                }.addOnCompleteListener { callBack.onComplete() }
                        }
                    }
                }

            }.addOnFailureListener {
                callBack.onFailure(it.message ?: "erro no servidor")
            }
    }

    override fun fetchUserPosts(uuid: String, callBack: RequestCallBack<List<Post>>) {
        //
        FirebaseFirestore.getInstance().collection("posts").document(uuid).collection("posts")
            .orderBy("timestamp", Query.Direction.DESCENDING).get().addOnSuccessListener { res ->
                val documents = res.documents
                val posts = mutableListOf<Post>()
                for (document in documents) {
                    val post = document.toObject(Post::class.java)
                    post?.let {
                        posts.add(it)
                    }

                }
                callBack.onSuccess(posts)


            }.addOnFailureListener {
                callBack.onFailure(it.message ?: "posts nao encontrado")
            }.addOnCompleteListener {
                callBack.onComplete()
            }
    }

    override fun followUser(uuid: String, isFollow: Boolean, callBack: RequestCallBack<Boolean>) {
        val uid = FirebaseAuth.getInstance().uid ?: throw RuntimeException("Usuario não logado")
        // atualiza a tabela de seguidores
        FirebaseFirestore.getInstance().collection("/followers").document(uuid)
            .update(
                "followers",
                if (isFollow) FieldValue.arrayUnion(uid) else FieldValue.arrayRemove(uid)
            ).addOnSuccessListener { res ->
                callBack.onSuccess(true)
            }.addOnFailureListener {
                // verifica se o erro é do firestoreexception
                val err = it as? FirebaseFirestoreException
                if (err?.code == FirebaseFirestoreException.Code.NOT_FOUND) {
                    // se for a primeira vez que ta seguindo entao ele cria o no
                    FirebaseFirestore.getInstance().collection("/followers").document(uuid)
                        .set(hashMapOf("followers" to listOf(uid))).addOnSuccessListener {
                            callBack.onSuccess(true)
                        }.addOnFailureListener {
                            callBack.onFailure(it.message ?: "falha ao criar seguidor")
                        }
                }
                callBack.onFailure(it.message ?: "falha ao seguir o usuario")
            }.addOnCompleteListener {
                callBack.onComplete()
            }

    }
}