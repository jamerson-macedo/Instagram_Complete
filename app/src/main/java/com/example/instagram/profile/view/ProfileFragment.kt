package com.example.instagram.profile.view

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.R
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.profile.FavAdapter
import com.example.instagram.profile.PostAdapter
import com.example.instagram.profile.Profile


class ProfileFragment() : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    FragmentProfileBinding::bind),Profile.View {
    private val postAdapter= PostAdapter()
    override fun showProgress(enable: Boolean) {
        binding?.progressProfile?.visibility=if(enable) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: UserAuth) {
       binding?.profileTxtPostCount?.text=userAuth.postCount.toString()
       binding?.profileTxtFollowing?.text=userAuth.FollowingCount.toString()
       binding?.profileTxtFollowers?.text=userAuth.FollowersgCount.toString()
       binding?.profileTxtUsername?.text=userAuth.name
       binding?.profileTxtBio?.text="TODO"
        presenter.fetchUserPost()

    }

    override fun displayRequestFailure(message: String) {
       Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    override fun displayEmptyPost() {
        binding?.txtempty?.visibility=View.VISIBLE
        binding?.profileRv?.visibility=View.GONE
    }

    override fun displayFullPost(posts: List<Post>) {
        binding?.txtempty?.visibility=View.GONE
        binding?.profileRv?.visibility=View.VISIBLE
        postAdapter.items=posts
        postAdapter.notifyDataSetChanged()
    }

    override lateinit var presenter: Profile.Presenter
    override fun setUpViews() {
        // recycler feed
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = postAdapter
        // recycler destaques
        binding?.rvProfileFavorites?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding?.rvProfileFavorites?.adapter = FavAdapter()
        presenter.fetchUserProfile()
    }

    override fun setUpPresenter() {
        // presenter=ProfilePresenter(this,repository)
    }


    override fun getMenu(): Int? {
        return R.menu.menu_toolbar_profile
    }

}