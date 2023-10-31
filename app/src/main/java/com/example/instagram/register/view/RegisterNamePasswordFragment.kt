package com.example.instagram.register.view

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.util.TxtWatcher
import com.example.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.example.instagram.register.RegisterNameAndPassword
import com.example.instagram.register.presenter.RegisterNamePasswordPresenter


class RegisterNamePasswordFragment() : Fragment(R.layout.fragment_register_name_password),
    RegisterNameAndPassword.View {
    private var binding: FragmentRegisterNamePasswordBinding? = null
    override lateinit var presenter: RegisterNameAndPassword.Presenter

    companion object {
        const val KEY_EMAIL = "key_email"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterNamePasswordBinding.bind(view)
        val repository=DependencyInjector.registerEmailRepository()
        presenter=RegisterNamePasswordPresenter(this,repository)
        // instanciando o presenter
        // desconpactando o vgalor da outra activity

        val email = arguments?.getString(KEY_EMAIL) ?: throw IllegalStateException("Email not found")

        binding?.apply {
            registerTxtLogin.setOnClickListener {
                activity?.finish()
            }
            registerNameBtnNext.setOnClickListener {
                presenter.create(
                    email, registerName.text.toString(),
                    registerPassword.text.toString(),
                    registerConfirmPassword.text.toString()
                )
            }
            registerName.addTextChangedListener(watcher)
            registerPassword.addTextChangedListener(watcher)
            registerConfirmPassword.addTextChangedListener(watcher)

            registerName.addTextChangedListener(TxtWatcher {
                // limpa os campos de erro
                nameDisplayFailure(null)
            })
            registerPassword.addTextChangedListener(TxtWatcher {
                // limpa os campos de erro
                passwordDisplayFailure(null)
            })
            registerConfirmPassword.addTextChangedListener(TxtWatcher {
                // limpa os campos de erro
                passwordDisplayFailure(null)
            })

        }

    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()

        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding?.registerNameBtnNext?.isEnabled = binding?.registerName?.text.toString()
            .isNotEmpty() && binding?.registerPassword?.text.toString()
            .isNotEmpty() && binding?.registerConfirmPassword?.text.toString().isNotEmpty()
    }

    override fun nameDisplayFailure(nameError: Int?) {
        binding?.registerEditNameInput?.error = nameError?.let { getString(it) }
    }

    override fun passwordDisplayFailure(passError: Int?) {
        binding?.registerEditPasswordInput?.error = passError?.let { getString(it) }

    }

    override fun onCreateSucces(name: String) {
       //
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnNext?.showProgress(enabled)
    }
}
