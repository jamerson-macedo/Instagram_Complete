package com.example.instagram.register.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // para gerar um container de fragments é preciso colocar um frame layout e depois inflar cada um
        val fragment=RegisterEmailFragment()
        supportFragmentManager.beginTransaction().apply {
            // o container e dps o fragment desejado
            add(R.id.register_fragment,fragment)
            commit()
        }
    }
}