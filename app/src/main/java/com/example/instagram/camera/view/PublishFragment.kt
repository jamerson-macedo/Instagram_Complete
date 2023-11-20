package com.example.instagram.camera.view

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.setFragmentResultListener
import com.example.instagram.R
import com.example.instagram.common.view.util.File

/**
 * A simple [Fragment] subclass.
 * Use the [PublishFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublishFragment : Fragment() {
    private lateinit var previewCamera: PreviewView
    private var imageCapture: ImageCapture? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewCamera = view.findViewById(R.id.preview_camera)
        view.findViewById<Button>(R.id.camera_picture).setOnClickListener {
            TakePhoto()
        }
    }

    private fun TakePhoto() {
        val imageCap = imageCapture ?: return
        val filepatch= File.generateFile(requireActivity())
        val outPutOptions=ImageCapture.OutputFileOptions.Builder(filepatch).build()
        imageCap.takePicture(outPutOptions,ContextCompat.getMainExecutor(requireContext()),object :ImageCapture.OnImageSavedCallback{
            override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                // pegando a foto salva
                val savedUri= Uri.fromFile(filepatch)
                setFragmentResult("takephotoKey", bundleOf("uri" to savedUri))
                Log.d("teste",savedUri.toString())

            }

            override fun onError(exception: ImageCaptureException) {
                Log.e("falha",exception.toString())

            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // recebendo o valor do add fragment
        setFragmentResultListener("cameraKey") { requestKey, bundle ->
            val sholdStart = bundle.getBoolean("startCamera") // aqui ele recebe true
            if (sholdStart) {
                startcamera()

            }

        }
    }

    private fun startcamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvide: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build()
                .also { it.setSurfaceProvider(previewCamera.surfaceProvider) }
            imageCapture=ImageCapture.Builder().build()
            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvide.unbindAll()
                cameraProvide.bindToLifecycle(this, cameraSelector, preview,imageCapture)

            } catch (e: Exception) {
                Log.i("cameralog", e.toString())

            }


        }, ContextCompat.getMainExecutor(requireContext()))


    }


}