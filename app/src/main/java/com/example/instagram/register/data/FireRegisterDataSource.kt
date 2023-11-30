package com.example.instagram.register.data

import android.net.Uri
import android.util.Log
import com.example.instagram.common.view.model.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

class FireRegisterDataSource : RegisterDataSource {
    // verifica o email na primeira tela
    override fun create(email: String, callback: RegisterCallback) {
        // pegoa referencia do email do banco e verifico se é igual o email que passei
        FirebaseFirestore.getInstance().collection("/users").whereEqualTo("email", email).get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("usuario ja cadastrado")
                }


            }.addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "erro interno do servidor")

            }.addOnCompleteListener {
                callback.onComplete()
            }
    }

    override fun create(email: String, name: String, password: String, callback: RegisterCallback) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { result ->
                val uid = result.user?.uid
                if (uid == null) {
                    callback.onFailure("erro interno do servidor")
                } else {
                    // gravar os dados
                    FirebaseFirestore.getInstance().collection("/users").document(uid).set(
                        hashMapOf(
                            "name" to name,
                            "email" to email,
                            "followers" to 0,
                            "following" to 0,
                            "postCount" to 0,
                            "uuid" to uid,
                            "photoUrl" to null
                        )
                    ).addOnSuccessListener {
                        callback.onSuccess()

                    }.addOnFailureListener {
                        callback.onFailure(it.message ?: "Erro no servidor")
                    }.addOnCompleteListener {
                        callback.onComplete()
                    }

                }

            }.addOnFailureListener { exception ->
                callback.onFailure(exception.message ?: "Erro interno")

            }
    }

    override fun updateUser(uri: Uri, callback: RegisterCallback) {
        val userAtual = FirebaseAuth.getInstance().uid

        if (userAtual == null || uri.lastPathSegment == null) {
            callback.onFailure("Usuario não encontrado")
            return
        }
        // referencia do storage
        val storageRef = FirebaseStorage.getInstance().reference
        // referencia do nó
        val imgRef = storageRef.child("images/").child(userAtual).child(uri.lastPathSegment!!)
        imgRef.putFile(uri).addOnSuccessListener { result ->
            // foto ja salva/ referencia do caminho
            imgRef.downloadUrl.addOnSuccessListener { res ->
                val userRef =
                    FirebaseFirestore.getInstance().collection("/users").document(userAtual)


                userRef.get().addOnSuccessListener { documents ->
                    val user = documents.toObject(User::class.java)
                    // copio e adiciono um novo
                    val newUser = user?.copy(photoUrl = res.toString())
                    if (newUser != null) {
                        userRef.set(newUser).addOnSuccessListener {
                            callback.onSuccess()
                        }.addOnFailureListener {
                            callback.onFailure(it.message ?: "erro no servidor")
                        }.addOnCompleteListener {
                            callback.onComplete()
                        }
                    }
                }
            }
        }.addOnFailureListener {
            callback.onFailure(it.message ?: "erro do servidor")
        }
    }
}