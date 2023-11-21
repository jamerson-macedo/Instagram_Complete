package com.example.instagram.home.view

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.post.view.AddFragment
import com.example.instagram.common.view.base.replaceFragment
import com.example.instagram.databinding.ActivityHomeBinding
import com.example.instagram.profile.view.ProfileFragment
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity(), AddFragment.Addlistener {
    private lateinit var navigationView: BottomNavigationView
    private lateinit var binding: ActivityHomeBinding
    private lateinit var homeFragment: HomeFragment
    private lateinit var searchFragment: Fragment
    private lateinit var cameraFragment: Fragment
    private lateinit var reelsFragment: Fragment
    private lateinit var profileFragment: ProfileFragment
    private var currentFragment: Fragment? = null

    @RequiresApi(api = Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // configurando a status bar
        window.statusBarColor = ContextCompat.getColor(this, R.color.gray)
        window.insetsController?.setSystemBarsAppearance(
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
            WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
        )
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.photoicon)
        supportActionBar?.setTitle("")


        navigationView = findViewById(R.id.bottom_navigation)

        homeFragment = HomeFragment()
        searchFragment = SearchFragment()
        cameraFragment = AddFragment()
        reelsFragment = ReelsFragment()
        profileFragment = ProfileFragment()

        // currentFragment = homeFragment

        //binding.bottomNavigation.selectedItemId = R.id.nav_home
        // adicionando todos na pilha
        // primeira forma de salvar os estados é inicializando tudo de uma vez e dps treocando

        /* supportFragmentManager.beginTransaction().apply {
             add(R.id.main_fragment,profileFragment,"4").hide(profileFragment)
             add(R.id.main_fragment,reelsFragment,"3").hide(reelsFragment)
             add(R.id.main_fragment,cameraFragment,"2").hide(cameraFragment)
             add(R.id.main_fragment,searchFragment,"1").hide(searchFragment)
             add(R.id.main_fragment,homeFragment,"0")
             commit()
         }
         */



        binding.bottomNavigation.setOnItemSelectedListener { item ->
            var scroolTollbar = false
            /* when (item.itemId) {
                   R.id.nav_home -> HomeFragment()
                   R.id.nav_search -> SearchFragment()
                   R.id.nav_publish -> PublishFragment()
                   R.id.nav_reels -> ReelsFragment()
                   R.id.nav_profile -> ProfileFragment()
                   else -> null
               }

               // verifico se o fragment atual é diferente do novo
               val currtFrament = supportFragmentManager.findFragmentById(R.id.main_fragment)
               val fragmentTag = newFragment?.javaClass?.simpleName

               if (!currtFrament?.tag.equals(fragmentTag)) {
                   currtFrament?.let { frag ->
                       fragmentsavedInstance.put(
                           frag?.tag!!,
                           supportFragmentManager.saveFragmentInstanceState(frag)
                       )
                       // passo o nome do fragment e o fragmentatual


                   }
               }
               // agora instancia apos salvar
               newFragment?.setInitialSavedState(fragmentsavedInstance[fragmentTag])


               // se ele existir
               newFragment?.let {
                   supportFragmentManager.beginTransaction()
                       .replace(R.id.main_fragment, it, fragmentTag).addToBackStack(fragmentTag)
                       .commit()

               }
               */


            when (item.itemId) {
                R.id.nav_home -> {
                    if (currentFragment == homeFragment) return@setOnItemSelectedListener false
                    // esconde o atual e troca para o que clicou
                    currentFragment = homeFragment
                    scroolTollbar = true

                }

                R.id.nav_search -> {
                    if (currentFragment == searchFragment) return@setOnItemSelectedListener false
                    currentFragment = searchFragment

                }

                R.id.nav_publish -> {
                    if (currentFragment == cameraFragment) return@setOnItemSelectedListener false
                    currentFragment = cameraFragment


                }

                R.id.nav_reels -> {
                    if (currentFragment == reelsFragment) return@setOnItemSelectedListener false

                    currentFragment = reelsFragment

                }

                R.id.nav_profile -> {
                    if (currentFragment == profileFragment) return@setOnItemSelectedListener false
                    currentFragment = profileFragment

                }

            }

            setScrollToolbarEnable(scroolTollbar)
            currentFragment?.let {
            replaceFragment(R.id.main_fragment,it)
            }
            return@setOnItemSelectedListener true
        }
        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }

    private fun setScrollToolbarEnable(enabled: Boolean) {
        // conmfigurando dinamicamente a acao da toolbar
        var params = binding.toolbar.layoutParams as AppBarLayout.LayoutParams
        var coordinatorparams = binding.appbar.layoutParams as CoordinatorLayout.LayoutParams
        if (enabled) {
            // se for pra ativar ele usa isso
            params.scrollFlags =
                AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS
            coordinatorparams.behavior = AppBarLayout.Behavior()
        } else {
            // resetando
            params.scrollFlags = 0
            coordinatorparams.behavior = null
        }
        binding.appbar.layoutParams = coordinatorparams
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return super.onOptionsItemSelected(item)
    }

    override fun onPostCreated() {
        homeFragment.presenter.clearCache()
        // verifica se o fragment ja foi  inicializado
        // tem que passar a tag como parametro na hora do replace
        if(supportFragmentManager.findFragmentByTag(profileFragment.javaClass.simpleName)!=null){
        profileFragment.presenter.clearCache()
        }
        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }


}

