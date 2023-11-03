package com.example.instagram.splash.view

import android.animation.Animator
import android.animation.Animator.AnimatorListener
import android.animation.AnimatorListenerAdapter
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.instagram.R
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.databinding.ActivitySplashBinding
import com.example.instagram.home.view.HomeActivity
import com.example.instagram.login.view.LoginActivity
import com.example.instagram.splash.Splash
import com.example.instagram.splash.presenter.PresenterSplash

class SplashActivity : AppCompatActivity(),Splash.View {
    private lateinit var binding:ActivitySplashBinding
    override lateinit var presenter: Splash.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val repository=DependencyInjector.SplashRepository()
        presenter=PresenterSplash(this,repository)

        binding.splashImg.animate().apply {
            // fica ouvindo a animacao
            setListener(object :AnimatorListenerAdapter(){
                override fun onAnimationEnd(animation: Animator) {
                    presenter.autenticated()
                }
            })
            duration=1000
            alpha(1.0f)
            start()
        }

    }
    override fun gotoMain() {
        val i = Intent(this, HomeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun gotoLogin() {
        val i = Intent(this, LoginActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}