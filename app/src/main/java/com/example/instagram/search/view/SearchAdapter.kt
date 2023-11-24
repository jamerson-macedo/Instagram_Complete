package com.example.instagram.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.common.view.model.UserAuth

class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var items: List<UserAuth> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_home_search, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.bind(items[position])
    }

    class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: UserAuth) {
            itemView.findViewById<ImageView>(R.id.search_img_user)
                .setImageURI(user.userPhoto)
            itemView.findViewById<TextView>(R.id.search_txt_username).text = user.name

        }
    }
}