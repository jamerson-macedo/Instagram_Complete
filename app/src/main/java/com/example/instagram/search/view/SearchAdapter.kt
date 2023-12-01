package com.example.instagram.search.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagram.R
import com.example.instagram.common.view.model.User
import com.example.instagram.common.view.model.UserAuth

class SearchAdapter(private val clickListenner:(String)->Unit) : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {
    var items: List<User> = mutableListOf()

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

   inner class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: User) {
            Glide.with(itemView.context).load(user.photoUrl).into( itemView.findViewById<ImageView>(R.id.search_img_user))
            itemView.findViewById<TextView>(R.id.search_txt_username).text = user.name
            itemView.setOnClickListener {
                if(user.uuid!=null){
                    clickListenner.invoke(user.uuid)
                }

            }

        }
    }
}