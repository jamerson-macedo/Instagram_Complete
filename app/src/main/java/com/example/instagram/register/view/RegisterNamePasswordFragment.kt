package com.example.instagram.register.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.R
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.util.TxtWatcher
import com.example.instagram.databinding.FragmentRegisterEmailBinding
import com.example.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.example.instagram.register.RegisterEmail
import com.example.instagram.register.presenter.RegisterPresenter


class RegisterNamePasswordFragment : Fragment(R.layout.fragment_register_name_password) {
    private var binding: FragmentRegisterNamePasswordBinding? = null
    companion object{
        const val KEY_EMAIL="key_email"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterNamePasswordBinding.bind(view)
        // instanciando o presenter
        val email=arguments?.getString(KEY_EMAIL)

    }

    override fun onDestroy() {
        binding = null

        super.onDestroy()
    }



}
