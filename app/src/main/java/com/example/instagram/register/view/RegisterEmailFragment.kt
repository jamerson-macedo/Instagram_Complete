package com.example.instagram.register.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.util.TxtWatcher
import com.example.instagram.databinding.FragmentRegisterEmailBinding
import com.example.instagram.register.RegisterEmail
import com.example.instagram.register.presenter.RegisterPresenter


class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {
    private var binding: FragmentRegisterEmailBinding? = null
    override lateinit var presenter: RegisterEmail.Presenter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)
        val repository = DependencyInjector.registerEmailRepository()
        // instanciando o presenter
        presenter = RegisterPresenter(this, repository)
        binding?.let {
            with(it) {

                registerBtnNext.setOnClickListener {
                    presenter.create(registerEditEmail.text.toString())
                }

                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }
                registerEditEmail.addTextChangedListener(watcher)
                registerEditEmail.addTextChangedListener(TxtWatcher {
                    emailDisplayFailure(null)
                })

            }
        }
    }


    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding?.registerBtnNext?.isEnabled = binding?.registerEditEmail?.text.toString()
            .isNotEmpty()
    }
    override fun emailDisplayFailure(emailError: Int?) {
        binding?.registerEditEmail?.error=emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String) {
        binding?.registerEditEmail?.error=message
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnNext?.showProgress(enabled)
    }

    override fun goToNameAndPassword(email: String) {

    }


}