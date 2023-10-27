package com.example.instagram.common.view.base

import com.example.instagram.login.Login
import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.login.presenter.LoginPresenter
import com.example.instagram.register.data.FakeRegisterDataSource
import com.example.instagram.register.data.RegisterEmailRepository

object DependencyInjector {
    // serve para obter a instacia sem precisar colocar na activity
     fun loginRepository():LoginRepository {
         return LoginRepository(FakeDataSource())
     }
    fun registerEmailRepository():RegisterEmailRepository {
        return RegisterEmailRepository(FakeRegisterDataSource())
    }

}