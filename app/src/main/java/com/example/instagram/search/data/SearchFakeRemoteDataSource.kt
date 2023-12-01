package com.example.instagram.search.data

import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.provider.ContactsContract.Data
import android.util.Log
import com.example.instagram.common.view.base.RequestCallBack
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth
import java.util.UUID

class SearchFakeRemoteDataSource : SearchDataSource{

    override fun fetchUsers(name: String, callBack: RequestCallBack<List<User>>) {
        Handler(Looper.getMainLooper()).postDelayed({
        val users=DataBase.userAuths.filter {
            // quando o ususario comecar com e for diferente do atual
            it.name.lowercase().startsWith(name.lowercase()) && it.uuid!= DataBase.sessionAuth!!.uuid

        }
            //callBack.onSuccess(users.toList())
            callBack.onComplete()
        }, 2000)
    }

}