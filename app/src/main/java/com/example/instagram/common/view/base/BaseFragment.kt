package com.example.instagram.common.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

abstract class BaseFragment<T, P : BasePresenter>(layoutId: Int, val bind: (View) -> T) :
    Fragment(layoutId) {
    abstract var presenter: P
    protected var binding: T? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getMenu()?.let {
            setHasOptionsMenu(true)
        }
        setUpPresenter()

    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        getMenu()?.let {
            inflater.inflate(it,menu)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)

        if(savedInstanceState==null){
            setUpViews()
        }

    }

    abstract fun setUpViews()
    abstract fun setUpPresenter()
    open fun getMenu(): Int? {
        return null
    }
}