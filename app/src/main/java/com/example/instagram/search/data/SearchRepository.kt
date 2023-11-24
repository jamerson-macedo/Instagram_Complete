package com.example.instagram.search.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth

class SearchRepository(val datasource:SearchDataSource) {
    fun fetchUsers(name: String, callBack: RequestCallBack<List<UserAuth>>) {
        datasource.fetchUsers(name, object : RequestCallBack<List<UserAuth>> {
            override fun onSuccess(data: List<UserAuth>) {
                callBack.onSuccess(data)
            }

            override fun onFailure(message: String) {
                callBack.onFailure(message)
            }

            override fun onComplete() {
                callBack.onComplete()
            }

        })
    }
}

