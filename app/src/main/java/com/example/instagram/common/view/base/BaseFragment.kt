package com.example.instagram.common.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.fragment.app.Fragment

// todos os presenters tem obase presenter entao quando eu coloco o presetner preciso ser do tipo base
abstract class BaseFragment<T, P : BasePresenter>

    (@LayoutRes layoutId: Int, val bind: (View) -> T) :
    Fragment(layoutId) {
    abstract var presenter: P
    // configurando o bind
    protected var binding: T? = null
    // o bind nao sabe quais os metodos do fragment que extendeu, entao tem que passar uma funcao com parametro
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // se tiver menu
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
        // se tiver menu
        getMenu()?.let {
            menu.clear()
            inflater.inflate(it,menu)
        }

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = bind(view)
        // vira isso
        // bindng=ProfileFragmentBind.bind(view)
        // so configura a view se for nulo
        if(savedInstanceState==null){
            setUpViews()
        }



    }


    abstract fun setUpViews()
    // setupview para configurar la no fragment que extende ao base fragment
    abstract fun setUpPresenter()
    // definindo quando o menu é obrigatorio ou nao
    @MenuRes
    open fun getMenu(): Int? {
        return null
    }
}