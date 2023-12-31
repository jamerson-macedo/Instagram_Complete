package com.example.instagram.post.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.instagram.R
import com.example.instagram.camera.Camera
import com.example.instagram.camera.view.AddActivity
import com.example.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class AddFragment : Fragment(R.layout.fragment_add){

    lateinit var presenter: Camera.Presenter
    private var binding: FragmentAddBinding? = null
    private var addlistener: Addlistener? = null


    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentAddBinding.bind(view)
        if (savedInstanceState == null) {
            setUpViews()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState != null) {
            startCamera()
        }
        setFragmentResultListener("takephotoKey") { requestKey, bundle ->

            val uri = bundle.getParcelable<Uri>("uri")
            uri?.let {
                // se a uri existir
                val intent = Intent(requireContext(), AddActivity::class.java)
                intent.putExtra("photoUri", uri)
                addActivityResult.launch(intent)

            }
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Addlistener) {
            addlistener = context
        }
    }


    private val addActivityResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                addlistener?.onPostCreated()
            }

        }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setUpViews() {
        val tabLayout = binding?.addTab
        val viewPager = binding?.addViewpager
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tabLayout != null && viewPager != null) {
            // nao deixar o fragment morrer
            tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.text == getString(adapter.tabsName[0])) {
                        // se for o fragment de camera
                        startCamera()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    //
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    //
                }

            })
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = getString(adapter.tabsName[position])
            }.attach()

        }
        // verificar permissoes
        if (allPermissionsGranted()) {
            startCamera()
        } else {
           getPermission.launch(permissions)
        }
    }

    private fun startCamera() {
        setFragmentResult("cameraKey", bundleOf("startCamera" to true))
        //
    }

    private val getPermission =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { granted ->
            Log.i("loglog",granted.toString())

            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(requireContext(), "Aceite a permissão", Toast.LENGTH_SHORT).show()
            }

        }




    private fun allPermissionsGranted() = (ContextCompat.checkSelfPermission(
        requireContext(),
        permissions[0]
    ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
        requireContext(),
        permissions[1]
    ) == PackageManager.PERMISSION_GRANTED)


    fun setUpPresenter() {
        //
    }
    companion object{
        @RequiresApi(Build.VERSION_CODES.TIRAMISU)
        val permissions = arrayOf(
            android.Manifest.permission.CAMERA,
           android.Manifest.permission.READ_MEDIA_IMAGES
        )
    }

    interface Addlistener {
        fun onPostCreated()
    }




}