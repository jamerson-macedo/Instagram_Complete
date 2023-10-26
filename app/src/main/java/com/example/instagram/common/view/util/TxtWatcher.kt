package com.example.instagram.common.view.util

import android.text.Editable
import android.text.TextWatcher
// quando eu quero apenas uma funcao de uma determinada classe eu estancio ela e passo como parametro o valor
class TxtWatcher(val onTextChanged: (String) -> Unit) : TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        onTextChanged(s.toString())
    }

    override fun afterTextChanged(s: Editable?) {

    }
}