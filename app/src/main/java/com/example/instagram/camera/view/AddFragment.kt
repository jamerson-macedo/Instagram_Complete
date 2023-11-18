package com.example.instagram.camera.view

import android.content.pm.PackageManager
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult

import com.example.instagram.R
import com.example.instagram.camera.Camera
import com.example.instagram.common.view.base.BaseFragment
import com.example.instagram.databinding.FragmentAddBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class AddFragment : BaseFragment<FragmentAddBinding, Camera.Presenter>(
    R.layout.fragment_add,
    FragmentAddBinding::bind
), Camera.View {
    override lateinit var presenter: Camera.Presenter
    override fun setUpViews() {
        val tabLayout = binding?.addTab
        val viewPager = binding?.addViewpager
        val adapter = AddViewPagerAdapter(requireActivity())
        viewPager?.adapter = adapter

        if (tabLayout != null && viewPager != null) {
            // nao deixar o fragment morrer
            tabLayout.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab?) {
                    if (tab?.text==getString(adapter.tabsName[0])){
                        // se for o fragment de camera
                        startCamera()
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                    TODO("Not yet implemented")
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
    private val getPermission=registerForActivityResult(ActivityResultContracts.RequestPermission()){granted->
        if(allPermissionsGranted()){
            startCamera()
        }else{
            Toast.makeText(requireContext(),"Aceite a permissão",Toast.LENGTH_SHORT).show()
        }
    }

    private fun allPermissionsGranted() = ContextCompat.checkSelfPermission(requireContext(), REQUIRED_PERMISSION)==PackageManager.PERMISSION_GRANTED
    companion object{
        const val REQUIRED_PERMISSION=android.Manifest.permission.CAMERA
    }

    override fun setUpPresenter() {
        //
    }
}