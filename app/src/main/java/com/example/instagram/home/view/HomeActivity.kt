package com.example.instagram.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.instagram.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {
    private lateinit var navigationView: BottomNavigationView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)


        navigationView = findViewById(R.id.bottom_navigation)
        val navController = findNavController(R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(navigationView, navController)

    }

}