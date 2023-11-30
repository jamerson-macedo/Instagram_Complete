package com.example.instagram.profile.view

import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.profile.PostAdapter
import com.example.instagram.profile.Profile
import com.example.instagram.profile.presenter.ProfilePresenter
import com.google.android.material.bottomnavigation.BottomNavigationView


class ProfileFragment() : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    // quando tem a mesma assinatura pode usar os 2 pontos
    FragmentProfileBinding::bind
), Profile.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private val postAdapter = PostAdapter()
    override lateinit var presenter: Profile.Presenter
    private var userId: String? = null


    override fun setUpPresenter() {
        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun showProgress(enable: Boolean) {
        binding?.progressProfile?.visibility = if (enable) View.VISIBLE else View.GONE
    }
    override fun setUpViews() {
        userId = arguments?.getString(KEY_USER_ID)
        // recycler feed
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = postAdapter
        binding?.profileNavTab?.setOnNavigationItemSelectedListener(this)
        binding?.profileBtnEditProfile?.setOnClickListener {
            if(it.tag==true){
              binding?.profileBtnEditProfile?.text="Follow"
                binding?.profileBtnEditProfile?.tag=false
                presenter.followUser(userId,false)
            }else if(it.tag==false){
                binding?.profileBtnEditProfile?.text="Unfolow"
                binding?.profileBtnEditProfile?.tag=true
                presenter.followUser(userId,true)
            }

        }
        presenter.fetchUserProfile(userId)

    }

    override fun displayUserProfile(user: Pair<User, Boolean?>) {
        // recebendo o par
        val (userAuth, following) = user

        binding?.profileTxtPostCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.following.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.followers.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"
        binding?.let {
            Glide.with(requireContext()).load(userAuth.photoUrl).into(it.profileImgIcon)
        }

        binding?.profileBtnEditProfile?.text=when(following){
            null->getString(R.string.edit_profile)
            true->"Unfollow"
            false->"Follow"
        }
        // guardando o booleano
        binding?.profileBtnEditProfile?.tag=following
        presenter.fetchUserPost(userId)

    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun displayEmptyPost() {
        binding?.profiletxt?.visibility = View.VISIBLE
        binding?.profileRv?.visibility = View.GONE


    }

    override fun displayFullPost(posts: List<Post>) {
        binding?.profiletxt?.visibility = View.GONE
        binding?.profileRv?.visibility = View.VISIBLE
        postAdapter.items = posts
        postAdapter.notifyDataSetChanged()
    }

    override fun displayFullFav(posts: List<Fav>) {
        //
    }





    override fun getMenu(): Int {
        return R.menu.menu_toolbar_profile
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_profile_grid -> binding?.profileRv?.layoutManager =
                GridLayoutManager(requireContext(), 3)

            R.id.nav_reels -> binding?.profileRv?.layoutManager =
                LinearLayoutManager(requireContext())
        }
        return true
    }

    companion object {
        const val KEY_USER_ID = "key_user_id"
    }

    /* override fun onViewStateRestored(savedInstanceState: Bundle?) {
         if(savedInstanceState!=null){
             val state=savedInstanceState.getParcelable<UserAuth>("mystate")
             state?.let {
                 displayUserProfile(it)
             }
         }
         super.onViewStateRestored(savedInstanceState)
     }

     */

}