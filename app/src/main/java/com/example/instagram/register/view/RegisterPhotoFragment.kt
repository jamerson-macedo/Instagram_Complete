package com.example.instagram.register.view

import android.content.Context
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import com.example.instagram.R
import com.example.instagram.common.view.CropperImageFragment.Companion.KEY_URI
import com.example.instagram.common.view.CustomDialog
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.databinding.FragmentRegisterPhotoBinding
import com.example.instagram.register.RegisterPhoto
import com.example.instagram.register.presenter.RegisterPhotoPresenter


class RegisterPhotoFragment : Fragment(R.layout.fragment_register_photo),RegisterPhoto.View {
    private var binding: FragmentRegisterPhotoBinding? = null
    private var fragmentAtachListener: FragmentAtachListener? = null
    override lateinit var presenter: RegisterPhoto.Presenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // recebendo a foto do outro fragment
        setFragmentResultListener("cropkey") { requestKey, bundle ->
            val uri = bundle.getParcelable<Uri>(KEY_URI)
            onCropImageResult(uri)
        }

    }

    private fun onCropImageResult(uri: Uri?) {
        if (uri != null) {
            // converter uri em bitmap
           val bitmap= if (Build.VERSION.SDK_INT > 28) {
                val source = ImageDecoder.createSource(requireContext().contentResolver, uri)
               ImageDecoder.decodeBitmap(source)
            }
            else{// se nao vamos fazer do jeito antigo
               MediaStore.Images.Media.getBitmap(requireContext().contentResolver, uri)
            }
            binding?.registerImgProfile?.setImageBitmap(bitmap)
            // depois que setou a imagem ai salva no banco
            presenter.updateUser(uri)


        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterPhotoBinding.bind(view)
        val repository=DependencyInjector.registerEmailRepository()
        presenter= RegisterPhotoPresenter(this,repository)

        binding?.apply {
            registerBtnJump.setOnClickListener {
                fragmentAtachListener?.goToMainScreen()
            }
            registerBtnAddphoto.isEnabled = true
            registerBtnAddphoto.setOnClickListener {
                openDialog()
            }
        }
    }


    override fun onDestroy() {
        // sempre que usar o biding tem que liberar a memoria
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    private fun openDialog() {
        val customdialog = CustomDialog(requireContext())
        customdialog.setTitle(R.string.app_name)
        customdialog.addButton(R.string.photo, R.string.galery) {
            when (it.id) {
                R.string.photo -> {
                    fragmentAtachListener?.openCamera()
                }
                R.string.galery -> {
                    // para abrir a galeria tem que chamar quem administra o fragment
                    fragmentAtachListener?.goToGalery()
                }
            }

        }
        customdialog.show()

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // verifica se o contexto ta implementando essa interface
        if (context is FragmentAtachListener) {
            fragmentAtachListener = context
        }
    }

    override fun onUpdateSucces() {
        fragmentAtachListener?.goToMainScreen()
    }

    override fun onUpdateFailure(message: String) {
       Toast.makeText(requireContext(),message,Toast.LENGTH_SHORT).show()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerBtnAddphoto?.showProgress(enabled)
    }




}