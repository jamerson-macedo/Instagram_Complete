package com.example.instagram.search.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FireSearchDataSource : SearchDataSource {
    override fun fetchUsers(name: String, callBack: RequestCallBack<List<User>>) {
        FirebaseFirestore.getInstance().collection("/users").whereGreaterThanOrEqualTo("name", name)
            .whereLessThanOrEqualTo("name", name + "\uf8ff").get().addOnSuccessListener { res ->
                // referencia do documento
                val documentref = res.documents
                // lista para retornar
                val users = mutableListOf<User>()
                for (document in documentref) {
                    val user = document.toObject(User::class.java)
                    if (user != null && user.uuid != FirebaseAuth.getInstance().uid){
                        users.add(user)
                    }
                }
                callBack.onSuccess(users)
            }.addOnFailureListener {
                callBack.onFailure(it.message ?: "erro no servidor ")

            }.addOnCompleteListener {
                callBack.onComplete()
            }
    }
}