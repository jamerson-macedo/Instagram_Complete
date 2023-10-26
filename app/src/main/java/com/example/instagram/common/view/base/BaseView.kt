package com.example.instagram.common.view.base

import com.example.instagram.login.Login
// ja pego uma variavel pronta de qualquer tipo
interface BaseView<T> {
    var presenter:T
}