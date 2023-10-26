package com.example.instagram.register.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.common.view.CustomDialog
import com.example.instagram.databinding.FragmentRegisterPhotoBinding


class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo) {
    private var binding:FragmentRegisterPhotoBinding?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding= FragmentRegisterPhotoBinding.bind(view)
        val customdialog = CustomDialog(requireContext())
        customdialog.setTitle(R.string.app_name)
        customdialog.addButton(R.string.photo, R.string.galery) {
            when (it.id) {
                R.string.photo -> {}
                R.string.galery -> {}
            }

        }
        customdialog.show()
    }
    override fun onDestroy() {
        // sempre que usar o biding tem que liberar a memoria
        binding=null
        super.onDestroy()
    }

}