package com.example.instagram.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.util.TxtWatcher
import com.example.instagram.databinding.ActivityLoginBinding
import com.example.instagram.home.view.HomeActivity
import com.example.instagram.login.Login
import com.example.instagram.login.data.FakeDataSource
import com.example.instagram.login.data.LoginRepository
import com.example.instagram.login.presenter.LoginPresenter
import com.example.instagram.register.view.RegisterActivity

class LoginActivity : AppCompatActivity(), Login.View {

    private lateinit var binding: ActivityLoginBinding
    override lateinit var presenter: Login.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // passando nossa activty
        // ao inves de insrtanciar na classe
        //val loginRepository = LoginRepository(FakeDataSource())
        presenter = LoginPresenter(this, DependencyInjector.loginRepository())


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
        binding.loginTxtRegister.setOnClickListener{

            openRegister()
        }

    }

    private fun openRegister() {
        startActivity(Intent(this,RegisterActivity::class.java))
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
        val i = Intent(this, HomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun onUserUnauthorized(message: String) {
        // mostrar um alerta
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    }
}