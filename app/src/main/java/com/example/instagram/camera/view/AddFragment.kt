package com.example.instagram.camera.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.instagram.R
import com.example.instagram.camera.Camera
import com.example.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : Fragment(R.layout.fragment_add) {

    lateinit var presenter: Camera.Presenter
    private var binding: FragmentAddBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
       binding=FragmentAddBinding.bind(view)
        if(savedInstanceState==null){
            setUpViews()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("takephotoKey"){ requestKey, bundle ->

            val uri=bundle.getParcelable<Uri>("uri")
            uri?.let {
                // se a uri existir
                val intent=Intent(requireContext(),AddActivity::class.java)
                intent.putExtra("photoUri",uri)
                startActivity(intent)

            }
        }
    }
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
            getPermission.launch(REQUIRED_PERMISSION)

        }
    }

    private fun startCamera() {
        setFragmentResult("cameraKey", bundleOf("startCamera" to true))
        //
    }

    private val getPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
            if (allPermissionsGranted()) {
                startCamera()
            } else {
                Toast.makeText(requireContext(), "Aceite a permissão", Toast.LENGTH_SHORT).show()
            }
        }

    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(
        requireContext(),
        REQUIRED_PERMISSION
    ) == PackageManager.PERMISSION_GRANTED

    companion object {
        const val REQUIRED_PERMISSION = android.Manifest.permission.CAMERA
    }

    fun setUpPresenter() {
        //
    }



}