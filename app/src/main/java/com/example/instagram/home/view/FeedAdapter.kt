package com.example.instagram.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.common.view.model.Post

class FeedAdapter() : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    var items: List<Post> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        return FeedViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_feed, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            Glide.with(itemView.context).load(post.photourl).into(itemView.findViewById<ImageView>(R.id.home_img_post))
            Glide.with(itemView.context).load(post.publisher?.photoUrl).into(itemView.findViewById<ImageView>(R.id.home_icon_user))
            itemView.findViewById<TextView>(R.id.home_txt_caption).text=post.caption
            itemView.findViewById<TextView>(R.id.home_txt_name).text=post.publisher?.name
            itemView.findViewById<TextView>(R.id.home_txt_user).text=(post.publisher?.name)
        }
    }
}