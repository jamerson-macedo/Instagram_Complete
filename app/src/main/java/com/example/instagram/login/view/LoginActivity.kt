package com.example.instagram.login.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.common.view.util.TxtWatcher
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.login.Login
import com.example.instagram.login.view.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // passando nossa activty
        presenter = LoginPresenter(this)


        binding.loginEditEmail.addTextChangedListener(watcher)
        // chama o metodo quando a pessoa volta a digitar
        binding.loginEditEmail.addTextChangedListener(TxtWatcher {
            emailDisplayFailure(null)
        })

        binding.loginEditPassword.addTextChangedListener(watcher)
        binding.loginEditPassword.addTextChangedListener(TxtWatcher {
            passWordDisplayFailure(null)
        })


        binding.loginBtnEnter.setOnClickListener {
// chamar o presenter
            presenter.login(
                binding.loginEditEmail.text.toString(),
                binding.loginEditPassword.text.toString()
            )
        }

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding.loginBtnEnter.isEnabled = binding.loginEditEmail.text.toString()
            .isNotEmpty() && binding.loginEditPassword.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.loginBtnEnter.showProgress(enabled)
    }

    override fun emailDisplayFailure(emailError: Int?) {
        binding.loginEditEmailInput.error = emailError?.let { getString(it) }
    }

    override fun passWordDisplayFailure(passWordError: Int?) {
        binding.loginEditPasswordInput.error = passWordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        // ir para tela principal
    }

    override fun onUserUnauthorized() {
        // mostrar um alerta
    }
}