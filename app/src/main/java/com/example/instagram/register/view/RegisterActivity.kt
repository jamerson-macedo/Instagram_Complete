package com.example.instagram.register.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.ActivityRegisterBinding
import com.example.instagram.home.view.HomeActivity
import com.example.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.example.instagram.register.view.RegisterWelcomeFragment.Companion.KEY_NAME

class RegisterActivity : AppCompatActivity(), FragmentAtachListener {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // para gerar um container de fragments é preciso colocar um frame layout e depois inflar cada um
        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)

    }

    override fun goToNameAndPassword(email: String) {
        var args = Bundle()
        args.putString(KEY_EMAIL, email)
        val registerEmailFragment = RegisterNamePasswordFragment()
        registerEmailFragment.arguments = args
        replaceFragment(registerEmailFragment)
    }

    override fun goToWelcomeScreen(nome: String) {
        var args = Bundle()
        args.putString(KEY_NAME, nome)
        val welcomeFragment = RegisterWelcomeFragment()
        welcomeFragment.arguments = args
        replaceFragment(welcomeFragment)
    }

    override fun goToUploadPhotoScreen() {

        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun replaceFragment(fragment: Fragment) {
        // verifica se jan foi iniciado
        if (supportFragmentManager.findFragmentById(R.id.register_fragment) == null) {
            supportFragmentManager.beginTransaction().apply {
                // o container e dps o fragment desejado
                // para empilhar os fragments addbackstack
                add(R.id.register_fragment, fragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                // o container e dps o fragment desejado
                // para empilhar os fragments addbackstack
                replace(R.id.register_fragment, fragment)
                addToBackStack(null)
                commit()
            }

        }

    }
}