package com.example.instagram.login.data

import com.google.firebase.auth.FirebaseAuth

class FireLoginDataSource : LoginDataSource {
    override fun login(email: String, password: String, callback: LoginCallback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                if (it.user != null) {
                    callback.onSuccess()
                } else {
                    callback.onFailure("Usuario não encontrado")
                }
            }.addOnFailureListener {
            callback.onFailure(it.message ?: "erro no servidor")
        }.addOnCompleteListener {
            callback.onComplete()
        }
    }
}