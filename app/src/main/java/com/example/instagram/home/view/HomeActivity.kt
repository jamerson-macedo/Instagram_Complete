package com.example.instagram.home.view

import android.os.Build
import android.os.Bundle
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.profile.view.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeFragment: Fragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var reelsFragment: Fragment
    private lateinit var profileFragment: Fragment
    private var currentFragment: Fragment? = null

    @RequiresApi(api = Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // configurando a status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )

        navigationView = findViewById(R.id.bottom_navigation)

        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        cameraFragment = PublishFragment()
        reelsFragment = ReelsFragment()
        profileFragment = ProfileFragment()

        binding.bottomNavigation.selectedItemId = R.id.nav_home
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    currentFragment = homeFragment
                }

                R.id.nav_search -> {
                    currentFragment = searchFragment
                }

                R.id.nav_publish -> {
                    currentFragment = cameraFragment
                }

                R.id.nav_reels -> {
                    currentFragment = reelsFragment
                }

                R.id.nav_profile -> {
                    currentFragment = profileFragment
                }

            }
            currentFragment?.let {
                if (supportFragmentManager.findFragmentById(R.id.main_fragment) == null) {
                    supportFragmentManager.beginTransaction().apply {
                        add(R.id.main_fragment, it)
                        commit()
                    }
                } else {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.main_fragment, it)
                        addToBackStack(null)
                        commit()
                    }
                }
            }
            true
        }
    }
}

