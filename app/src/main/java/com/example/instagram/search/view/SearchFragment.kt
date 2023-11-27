package com.example.instagram.search.view

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.instagram.R
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.model.UserAuth
import com.example.instagram.databinding.FragmentSearchBinding
import com.example.instagram.search.Search
import com.example.instagram.search.presenter.SearchPresenter


class SearchFragment : BaseFragment<FragmentSearchBinding, Search.Presenter>(
    R.layout.fragment_search,
    FragmentSearchBinding::bind
), Search.View {


    override lateinit var presenter: Search.Presenter
    private var searchListener: SearchListener? = null

    private val searchAdapter = SearchAdapter() { userid ->
        searchListener?.goToProfile(userid)

    }


    override fun setUpViews() {
        binding?.searchRv?.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding?.searchRv?.adapter = searchAdapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is SearchListener){
            searchListener=context
        }
    }

    override fun setUpPresenter() {
        presenter = SearchPresenter(this, DependencyInjector.searchRepository())
    }

    override fun getMenu() = R.menu.menu_search
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        val searchManage =
            requireActivity().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        (menu.findItem(R.id.menu_search).actionView as SearchView).apply {
            setSearchableInfo(searchManage.getSearchableInfo(requireActivity().componentName))
        }.apply {
            setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText?.isNotEmpty() == true) {
                        presenter.fetchUser(newText)
                        return true
                    }
                    return false
                }

            })
        }
    }

    override fun showProgress(enavled: Boolean) {
        binding?.progressSearch?.visibility = if (enavled) View.VISIBLE else View.GONE
    }

    override fun displayFullUsers(user: List<UserAuth>) {
        binding?.searchTxt?.visibility = View.GONE
        binding?.searchRv?.visibility = View.VISIBLE
        searchAdapter.items = user
        searchAdapter.notifyDataSetChanged()
    }

    override fun displayEmpty() {
        binding?.searchTxt?.visibility = View.VISIBLE
        binding?.searchRv?.visibility = View.GONE
    }

    interface SearchListener {
        fun goToProfile(uuid: String)
    }

}