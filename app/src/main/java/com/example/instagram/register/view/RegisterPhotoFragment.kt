package com.example.instagram.register.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.instagram.R
import com.example.instagram.common.view.CustomDialog


class RegisterPhotoFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val customdialog=CustomDialog(requireContext())
        customdialog.setTitle(R.string.app_name)
        customdialog.addButton(R.string.photo,R.string.galery){
        when(it.id){
            R.string.photo->{}
            R.string.galery->{}
        }

        }
        customdialog.show()
    }

}