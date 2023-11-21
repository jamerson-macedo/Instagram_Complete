package com.example.instagram.common.view.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.instagram.R

fun AppCompatActivity.replaceFragment(id:Int,fragment:Fragment){
    if (supportFragmentManager.findFragmentById(id) == null) {
        supportFragmentManager.beginTransaction().apply {
            add(R.id.main_fragment, fragment,fragment.javaClass.simpleName)
            commit()
        }
    } else {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.main_fragment, fragment,fragment.javaClass.simpleName)
            addToBackStack(null)
            commit()
        }
    }
}