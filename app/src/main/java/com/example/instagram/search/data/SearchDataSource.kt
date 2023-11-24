package com.example.instagram.search.data

import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.UserAuth

interface SearchDataSource {
    fun fetchUsers(name:String,callBack: RequestCallBack <List<UserAuth>>)
}