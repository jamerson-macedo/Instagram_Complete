package com.example.instagram.home.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.databinding.FragmentHomeBinding
import com.example.instagram.profile.Profile

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : BaseFragment<FragmentHomeBinding, Profile.Presenter>(R.layout.fragment_home,FragmentHomeBinding::bind) {
    override lateinit var presenter: Profile.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv_feed = view.findViewById<RecyclerView>(R.id.home_rv_feed)
        rv_feed.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_feed.adapter = FeedAdapter()

        // recycler destaques
        //val rv_profile_fav = view.findViewById<RecyclerView>(R.id.home_rv_stories)
       // rv_profile_fav.layoutManager =
        //    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
       // rv_profile_fav.adapter = StoriesAdapter()
    }

    override fun setUpViews() {
       //
    }

    override fun setUpPresenter() {
        //
    }

    override fun getMenu(): Int? {
        return R.menu.menu_toolbar
    }
    private class StoriesAdapter() : RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoriesViewHolder {
            return StoriesViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_stories, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: StoriesViewHolder, position: Int) {
            holder.bind(R.drawable.profile)
        }

        class StoriesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(imageView: Int) {
                itemView.findViewById<ImageView>(R.id.item_home_stories)
                    .setImageResource(imageView)
            }
        }
    }

    private class FeedAdapter() : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
            return FeedViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_feed, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
            holder.bind(R.drawable.profile)
        }

        class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(imageView: Int) {
                itemView.findViewById<ImageView>(R.id.home_icon_user)
                    .setImageResource(imageView)
            }
        }
    }


}