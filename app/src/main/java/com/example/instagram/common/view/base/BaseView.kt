package com.example.instagram.common.view.base

import com.example.instagram.login.Login
// ja pego uma variavel pronta de qualquer tipo
interface BaseView<T> {
    // todos vao ter essa variavel
    // vai ser uma fvariavel do tipo de presenter que extender
    var presenter:T
}