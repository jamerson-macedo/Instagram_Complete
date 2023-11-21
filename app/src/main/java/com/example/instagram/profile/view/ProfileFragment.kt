package com.example.instagram.profile.view

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instagram.R
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.model.Fav
import com.example.instagram.common.view.model.Post
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.databinding.FragmentProfileBinding
import com.example.instagram.profile.FavAdapter
import com.example.instagram.profile.PostAdapter
import com.example.instagram.profile.Profile
import com.example.instagram.profile.presenter.ProfilePresenter


class ProfileFragment() : BaseFragment<FragmentProfileBinding, Profile.Presenter>(
    R.layout.fragment_profile,
    // quando tem a mesma assinatura pode usar os 2 pontos
    FragmentProfileBinding::bind
), Profile.View {

    private val postAdapter = PostAdapter()
    override lateinit var presenter: Profile.Presenter



    override fun setUpPresenter() {
        val repository = DependencyInjector.profileRepository()
        presenter = ProfilePresenter(this, repository)
    }

    override fun showProgress(enable: Boolean) {
        binding?.progressProfile?.visibility = if (enable) View.VISIBLE else View.GONE
    }

    override fun displayUserProfile(userAuth: UserAuth) {
        binding?.profileTxtPostCount?.text = userAuth.postCount.toString()
        binding?.profileTxtFollowingCount?.text = userAuth.FollowingCount.toString()
        binding?.profileTxtFollowersCount?.text = userAuth.FollowersgCount.toString()
        binding?.profileTxtUsername?.text = userAuth.name
        binding?.profileTxtBio?.text = "TODO"
        binding?.profileImgIcon?.setImageURI(userAuth.userPhoto)
        presenter.fetchUserPost()

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


    override fun setUpViews() {
        // recycler feed
        binding?.profileRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.profileRv?.adapter = postAdapter
        presenter.fetchUserProfile()

    }


    override fun getMenu(): Int {
        return R.menu.menu_toolbar_profile
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