package com.example.instagram.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.fragment.app.findFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R



class ProfileFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv_profile=view.findViewById<RecyclerView>(R.id.profile_rv)
        rv_profile.layoutManager=GridLayoutManager(requireContext(),3)
        rv_profile.adapter=PostAdapter()
        val rv_profile_fav=view.findViewById<RecyclerView>(R.id.rv_profile_favorites)
        rv_profile_fav.layoutManager=LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        rv_profile_fav.adapter=FavAdapter()


    }
    private class PostAdapter():RecyclerView.Adapter<PostAdapter.PostViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
          return PostViewHolder(
             LayoutInflater.from(parent.context).inflate(R.layout.item_profile_grid,parent,false)
             )
        }

        override fun getItemCount(): Int {
           return 30
        }

        override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
            holder.bind(R.drawable.profile)
        }
        class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            fun bind(imageView: Int){
                itemView.findViewById<ImageView>(R.id.item_profile_img_grid).setImageResource(imageView)


            }
        }
    }

    private class FavAdapter():RecyclerView.Adapter<FavAdapter.FavViewHolder>(){


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
            return FavViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_profile_favorite, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
            holder.bind(R.drawable.post_icon)
        }
        class FavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(imageView: Int){

                itemView.findViewById<ImageView>(R.id.item_profile_img_fav).setImageResource(imageView)
            }
        }


    }

}