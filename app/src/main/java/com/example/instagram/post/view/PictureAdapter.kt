package com.example.instagram.post.view

import android.net.Uri
import android.os.Build
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R
import com.example.instagram.common.view.model.Post

class PictureAdapter(private val onClick:(Uri)->Unit): RecyclerView.Adapter<PictureAdapter.PictureViewHolder>() {
    var items:List<Uri> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureViewHolder {
        return PictureViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_profile_grid, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: PictureViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class PictureViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(imageView: Uri) {
            // pegando a foto no tamannho reduzido
            val bitmap=itemView.context.contentResolver.loadThumbnail(imageView, Size(200,200),null)
            itemView.findViewById<ImageView>(R.id.item_profile_img_grid)
                .setImageBitmap(bitmap)
            itemView.setOnClickListener {
                onClick.invoke(imageView)
            }
        }
    }
}