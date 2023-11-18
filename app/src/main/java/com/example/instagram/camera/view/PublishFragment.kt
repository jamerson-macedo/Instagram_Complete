package com.example.instagram.camera.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import com.example.instagram.R
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass.
 * Use the [PublishFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class PublishFragment : Fragment() {
    private lateinit var previewCamera:PreviewView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_publish, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        previewCamera=view.findViewById(R.id.preview_camera)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("cameraKey"){requestKey, bundle ->
           val sholdStart= bundle.getBoolean("startCamera")
            if(sholdStart){
                startcamera()

            }
            
        }
    }

    private fun startcamera() {
        val cameraProviderFuture=ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            val cameraProvide:ProcessCameraProvider=cameraProviderFuture.get()
            val preview=Preview.Builder().build().also { it.setSurfaceProvider(previewCamera.surfaceProvider)}
            val cameraSelector=CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvide.unbindAll()
                cameraProvide.bindToLifecycle(this,cameraSelector,preview)

            }catch (e:Exception){
                Log.i("cameralog",e.toString())

            }


        },ContextCompat.getMainExecutor(requireContext()))


    }


}