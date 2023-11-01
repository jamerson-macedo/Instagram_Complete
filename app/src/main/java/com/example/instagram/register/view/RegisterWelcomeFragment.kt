package com.example.instagram.register.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.FragmentRegisterNamePasswordBinding
import com.example.instagram.databinding.FragmentRegisterPhotoBinding
import com.example.instagram.databinding.FragmentRegisterWelcomeBinding

class RegisterWelcomeFragment : Fragment(R.layout.fragment_register_welcome) {
    private var binding: FragmentRegisterWelcomeBinding? = null
    private var fragmentAtachListener: FragmentAtachListener? = null

    companion object {
        const val KEY_NAME = "key_name"
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterWelcomeBinding.bind(view)
        val name = arguments?.getString(KEY_NAME) ?: throw IllegalStateException("nao encontrado")
        binding?.apply {
            txtWelcome.text = getString(R.string.welcome_instagram, name)
            registerBtnNext.isEnabled = true
            registerBtnNext.setOnClickListener {
                fragmentAtachListener?.goToUploadPhotoScreen()

            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is FragmentAtachListener) {
            // GUAREDA A REFERENCIA DA ACTIVITY
            fragmentAtachListener = context

        }
    }
}
