package com.example.instagram.home.view

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.home.Home
import com.example.instagram.home.data.HomeRepository
import com.example.instagram.home.presenter.HomePresenter
import com.example.instagram.profile.PostAdapter
import com.example.instagram.profile.Profile

class HomeFragment : BaseFragment<FragmentHomeBinding, Home.Presenter>(R.layout.fragment_home,FragmentHomeBinding::bind),Home.View {


    override lateinit var presenter: Home.Presenter
    private val postAdapter=FeedAdapter()


    override fun setUpViews() {
       binding?.homeRvFeed?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.homeRvFeed?.adapter =postAdapter
        // recycler destaques
        //val rv_profile_fav = view.findViewById<RecyclerView>(R.id.home_rv_stories)
        // rv_profile_fav.layoutManager =
        //    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        // rv_profile_fav.adapter = StoriesAdapter()
        presenter.fetchPost()

    }

    override fun setUpPresenter() {
        presenter=HomePresenter(this,DependencyInjector.homeRepository())
    }
    override fun getMenu()= R.menu.menu_toolbar
    override fun showProgress(enable: Boolean) {
        binding?.progressHome?.visibility=if(enable) View.VISIBLE else View.GONE
//
    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun displayEmptyPost() {
        binding?.hometxt?.visibility = View.VISIBLE
        binding?.homeRvFeed?.visibility = View.GONE
    }

    override fun displayFullPost(posts: List<Post>) {
        binding?.hometxt?.visibility = View.GONE
        binding?.homeRvFeed?.visibility = View.VISIBLE
        postAdapter.items = posts
        postAdapter.notifyDataSetChanged()

    }

    override fun displayFullFav(posts: List<Fav>) {
        //
    }


}