package com.example.instagram.view.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.R
import com.google.android.material.textfield.TextInputLayout

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val editTextEmail = findViewById<EditText>(R.id.login_edit_email)
        val editTextPassWord = findViewById<EditText>(R.id.login_edit_password)

        editTextEmail.addTextChangedListener(watecher)
        editTextPassWord.addTextChangedListener(watecher)
        val buttonLogin = findViewById<LoadingButton>(R.id.login_btn_enter)
        buttonLogin.setOnClickListener {
            buttonLogin.showProgress(true)
            findViewById<TextInputLayout>(R.id.login_edit_email_input)
                .error = "Email invalido !"
            findViewById<TextInputLayout>(R.id.login_edit_password_input)
                .error = "senha Invalida !"
            Handler(Looper.getMainLooper()).postDelayed({
                buttonLogin.showProgress(false)
            }, 2000)
        }

    }

    private val watecher = object : TextWatcher {
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
           //
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            // verifica se o campo de texto nao esta vazio
            // a logica é, se tive algo digitado ele é ativado
            findViewById<LoadingButton>(R.id.login_btn_enter).isEnabled = s.toString().isNotEmpty()
        }

        override fun afterTextChanged(s: Editable?) {
           //
        }


    }
}