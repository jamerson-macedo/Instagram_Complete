package com.example.instagram.post.view

import android.net.Uri
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.recyclerview.widget.GridLayoutManager
import com.example.instagram.R
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.databinding.FragmentGalleryBinding
import com.example.instagram.post.Post
import com.example.instagram.post.presenter.PostPresenter

/**
 * A simple [Fragment] subclass.
 * Use the [GaleryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GaleryFragment() : BaseFragment<FragmentGalleryBinding, Post.Presenter>(
    R.layout.fragment_gallery,
    FragmentGalleryBinding::bind
), Post.View {


    override lateinit var presenter: Post.Presenter
    private val pictureAdapter = PictureAdapter() { uri ->
        binding?.galleryImg?.setImageURI(uri)
        // quando clica ele sobe
        binding?.galeryNested?.smoothScrollTo(0, 0)
        presenter.selectedUri(uri)
    }

    override fun setUpViews() {
        binding?.galleryRv?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.galleryRv?.adapter = pictureAdapter
        presenter.fetchPictures()
    }

    override fun setUpPresenter() {
        presenter = PostPresenter(this, DependencyInjector.postRepository(requireContext()))

    }

    override fun showProgress(enable: Boolean) {
        binding?.progressGalery?.visibility = if (enable) View.VISIBLE else View.GONE
    }

    override fun displayFullPictures(posts: List<Uri>) {
        binding?.galerytxt?.visibility = View.GONE
        binding?.galleryRv?.visibility = View.VISIBLE
        pictureAdapter.items = posts
        pictureAdapter.notifyDataSetChanged()
        presenter.selectedUri(posts.first())


    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    override fun displayEmptyPictures() {
        binding?.galerytxt?.visibility = View.VISIBLE
        binding?.galleryRv?.visibility = View.GONE
    }

    override fun getMenu()=R.menu.menu_add
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_share->{
                setFragmentResult("takephotoKey", bundleOf("uri" to presenter.getselectedUri()))
            }
        }
        return super.onOptionsItemSelected(item)
    }


}