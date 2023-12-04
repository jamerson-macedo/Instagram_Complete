package com.example.instagram.camera.data

import android.net.Uri
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.User
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FireAddDataSource : AddDataSource {
    override fun createPost(
        uuid: String,
        photo: Uri,
        caption: String,
        callBack: RequestCallBack<Boolean>
    ) {
        // referencia da imagem
        val lastPathImg = photo.lastPathSegment ?: throw IllegalArgumentException("imagem invalida")
        // pegando a referencia do acminho da foto no banco
        // aqui a foto ja foi
        val imgStorage =
            FirebaseStorage.getInstance().reference.child("images/").child(uuid).child(lastPathImg)
        imgStorage.putFile(photo).addOnSuccessListener { res ->
            // agora pego a referencia no banco
            imgStorage.downloadUrl.addOnSuccessListener { resDownload ->
                // agora preciso anexar esas foto com um usuario
               val meRef= FirebaseFirestore.getInstance().collection("/users").document(uuid)
                   meRef.get()
                    .addOnSuccessListener { user ->
                        val me = user.toObject(User::class.java)
                        val postRef =
                            FirebaseFirestore.getInstance().collection("/posts").document(uuid)
                                .collection("posts").document()
                        val post = Post(
                            UUID = postRef.id,
                            photourl = resDownload.toString(),
                            caption = caption,
                            timestamp = System.currentTimeMillis(),
                            publisher = me
                        )
                        postRef.set(post).addOnSuccessListener { resPost ->
                            // incrementando o post
                            meRef.update("postCount",FieldValue.increment(1))

                            // meu feed
                            FirebaseFirestore.getInstance().collection("/feeds").document(uuid)
                                .collection("posts").document(postRef.id).set(post)
                                .addOnSuccessListener { meuFeed ->
                                    // feed com meus seguidores
                                    FirebaseFirestore.getInstance().collection("/followers")
                                        .document(uuid).get()
                                        .addOnSuccessListener { resFollowers ->
                                            if (resFollowers.exists()) {
                                                val list =
                                                    resFollowers.get("followers") as List<String>
                                                for (followerId in list) {
                                                    FirebaseFirestore.getInstance()
                                                        .collection("/feeds")
                                                        .document(followerId).collection("posts")
                                                        .document(postRef.id).set(post)
                                                }
                                            }

                                            callBack.onSuccess(true)


                                        }.addOnFailureListener {
                                            callBack.onFailure(
                                                it.message ?: "falha ao buscar meus seguidores"
                                            )
                                        }.addOnCompleteListener {
                                            callBack.onComplete()
                                        }


                                }

                        }.addOnFailureListener {
                            callBack.onFailure(it.message ?: "falha ao inserir o post")

                        }

                    }.addOnFailureListener {
                        callBack.onFailure(it.message ?: "falha ao buscar usuario")

                    }.addOnFailureListener {
                        callBack.onFailure(it.message ?: "falha ao baixar a foto")
                    }

            }.addOnFailureListener {
                callBack.onFailure(it.message ?: "erro no servidor")
            }
        }
    }
}