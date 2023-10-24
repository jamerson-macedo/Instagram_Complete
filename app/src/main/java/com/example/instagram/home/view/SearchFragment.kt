package com.example.instagram.home.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.R


class SearchFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rv_search = view.findViewById<RecyclerView>(R.id.search_rv)
        rv_search.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_search.adapter = SearchAdapter()
    }
    private class SearchAdapter() : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
            return SearchViewHolder(
                LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_home_search, parent, false)
            )
        }

        override fun getItemCount(): Int {
            return 30
        }

        override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
            holder.bind(R.drawable.profile)
        }

        class SearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun bind(imageView: Int) {
                itemView.findViewById<ImageView>(R.id.search_img_user)
                    .setImageResource(imageView)
            }
        }
    }


}