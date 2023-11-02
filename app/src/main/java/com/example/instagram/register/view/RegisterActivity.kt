package com.example.instagram.register.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import com.example.instagram.R
import com.example.instagram.common.view.CropperImageFragment
import com.example.instagram.common.view.CropperImageFragment.Companion.KEY_URI
import com.example.instagram.databinding.ActivityRegisterBinding
import com.example.instagram.home.view.HomeActivity
import com.example.instagram.register.view.RegisterNamePasswordFragment.Companion.KEY_EMAIL
import com.example.instagram.register.view.RegisterWelcomeFragment.Companion.KEY_NAME
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class RegisterActivity : AppCompatActivity(), FragmentAtachListener {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var currentPhoto: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // para gerar um container de fragments é preciso colocar um frame layout e depois inflar cada um
        val fragment = RegisterEmailFragment()
        replaceFragment(fragment)

    }

    override fun goToNameAndPassword(email: String) {
        var args = Bundle()
        args.putString(KEY_EMAIL, email)
        val registerEmailFragment = RegisterNamePasswordFragment()
        registerEmailFragment.arguments = args
        replaceFragment(registerEmailFragment)
    }

    override fun goToWelcomeScreen(nome: String) {
        var args = Bundle()
        args.putString(KEY_NAME, nome)
        val welcomeFragment = RegisterWelcomeFragment()
        welcomeFragment.arguments = args
        replaceFragment(welcomeFragment)
    }

    override fun goToUploadPhotoScreen() {

        val fragment = RegisterPhotoFragment()
        replaceFragment(fragment)
    }

    override fun goToMainScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    // codigo padrao para retornar a foto
    val getcontent = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            Log.d("PhotoPicker", "Selected URI: $uri")
        } else {
            Log.d("PhotoPicker", "No media selected")
        }
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_URI, uri)
            }
        }

        replaceFragment(fragment)
    }

    override fun goToGalery() {
        // passo o tipo de conteudo que eu quero

        getcontent.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }


    private val getCamera=registerForActivityResult(ActivityResultContracts.TakePicture()){saved->
        // se a foto foi salva
        if(saved){
            openImageCroper(currentPhoto)
        }

    }

    override fun openCamera() {
        // acao para abrir a camera
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(packageManager) != null) {
            // retorna um arquivo temporario
           val photoFile:File?= try {
                createImageFile()
            } catch (e: IOException) {
                Log.e("RegisterActivity",e.message.toString())
               null
            }
            photoFile?.also {
                val photoUri=FileProvider.getUriForFile(this,"com.example.instagram.fileprovider",it)
                currentPhoto=photoUri
                getCamera.launch(photoUri)
            }

        }
    }

    @Throws(IOException::class)
    private fun createImageFile(): File {
        // formato do caminho da foto
        val timeStamp=SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        // so quem le ;e esse app
        val diretorio=getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile("JPEG_{$timeStamp}_",".jpg",diretorio)
    }

    private fun replaceFragment(fragment: Fragment) {
        // verifica se jan foi iniciado
        if (supportFragmentManager.findFragmentById(R.id.register_fragment) == null) {
            supportFragmentManager.beginTransaction().apply {
                // o container e dps o fragment desejado
                // para empilhar os fragments addbackstack
                add(R.id.register_fragment, fragment)
                commit()
            }
        } else {
            supportFragmentManager.beginTransaction().apply {
                // o container e dps o fragment desejado
                // para empilhar os fragments addbackstack
                replace(R.id.register_fragment, fragment)
                addToBackStack(null)
                commit()
            }

        }

    }
    private fun openImageCroper(uri:Uri){
        val fragment = CropperImageFragment().apply {
            arguments = Bundle().apply {
                putParcelable(KEY_URI, currentPhoto)
            }
        }

        replaceFragment(fragment)
    }
}