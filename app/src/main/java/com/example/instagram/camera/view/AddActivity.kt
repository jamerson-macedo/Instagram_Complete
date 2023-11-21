package com.example.instagram.camera.view

import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.instagram.R
import com.example.instagram.camera.Camera
import com.example.instagram.camera.presenter.AddPresenter
import com.example.instagram.common.view.base.DependencyInjector
import com.example.instagram.common.view.model.DataBase
import com.example.instagram.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity(), Camera.View {
    private lateinit var binding: ActivityAddBinding


    override lateinit var presenter: Camera.Presenter
    lateinit var uri: Uri
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        // pegando a imagem que vem do fragment
        uri = intent.extras?.getParcelable<Uri>("photoUri")
            ?: throw RuntimeException("foto nao encontrada")
        binding.addImg.setImageURI(uri)
        presenter = AddPresenter(this, DependencyInjector.addRepository())
        setContentView(binding.root)
        setSupportActionBar(binding.toolbarAdd)

        val drawble = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back)
        supportActionBar?.setHomeAsUpIndicator(drawble)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun showProgress(enabled: Boolean) {
        binding.addProgress.visibility = if (enabled) View.VISIBLE else View.GONE

    }

    override fun displayRequestSuccess() {
        setResult(RESULT_OK)
        finish()


    }

    override fun displayRequestFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_add, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }

            R.id.action_share -> {

                presenter.createPost(uri, binding.addEditText.text.toString())

                return true
            }

        }
        return super.onOptionsItemSelected(item)
    }
}