package com.example.instagram.common.view

import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.instagram.R
import com.example.instagram.databinding.FragmentImageCropperBinding
import java.io.File

class CropperImageFragment : Fragment(R.layout.fragment_image_cropper) {
    private var binding: FragmentImageCropperBinding? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageCropperBinding.bind(view)
        val uri = arguments?.getParcelable<Uri>(KEY_URI)
        binding?.apply {
            cropperContainer.setAspectRatio(1, 1)
            cropperContainer.setFixedAspectRatio(true)
            cropperContainer.setImageUriAsync(uri)
            cropperBtnCancel.setOnClickListener {
                parentFragmentManager.popBackStack()
            }
            cropperContainer.setOnCropImageCompleteListener { view, result ->
                Log.i("fototaaqui",result.toString())
                // mandando para o fragment anterior que ;e o de esciolhad e galeria ou camera
                setFragmentResult("cropkey", bundleOf(KEY_URI to result.uri))
                parentFragmentManager.popBackStack()

            }
            cropperBtnSave.setOnClickListener {
                // busncando a foto
                val dir = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                // se ele consegiu
                if (dir != null) {
                    val uritoASave = Uri.fromFile(
                        File(
                            dir.path,
                            System.currentTimeMillis().toString() + ".jpeg"
                        )
                    )
                    cropperContainer.saveCroppedImageAsync(uritoASave)
                }
            }

        }
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()

    }

    companion object {
        const val KEY_URI = "key_uri"
    }
}