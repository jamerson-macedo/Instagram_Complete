package com.example.instagram.post.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.instagram.R

class AddViewPagerAdapter(fa:FragmentActivity):FragmentStateAdapter(fa) {
    val tabsName= arrayOf(R.string.photo,R.string.gallery)
    override fun getItemCount()=tabsName.size

    override fun createFragment(position: Int): Fragment {
        return when(tabsName[position]){
            R.string.photo-> PublishFragment()
            R.string.gallery-> GaleryFragment()
            else -> throw  IllegalStateException("Fragmento nao existe")
        }
    }
}