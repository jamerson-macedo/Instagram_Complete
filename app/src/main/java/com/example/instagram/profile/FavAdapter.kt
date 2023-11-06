package com.example.instagram.profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R

class FavAdapter() : RecyclerView.Adapter<FavAdapter.FavViewHolder>() {


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
        fun bind(imageView: Int) {

            itemView.findViewById<ImageView>(R.id.item_profile_img_fav)
                .setImageResource(imageView)
        }
    }
}