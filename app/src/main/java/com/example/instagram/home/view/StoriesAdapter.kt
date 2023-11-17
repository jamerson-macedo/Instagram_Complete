package com.example.instagram.home.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R

 class StoriesAdapter() : RecyclerView.Adapter<StoriesAdapter.StoriesViewHolder>() {


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

