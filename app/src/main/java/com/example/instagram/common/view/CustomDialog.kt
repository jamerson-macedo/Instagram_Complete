package com.example.instagram.common.view

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.example.instagram.R
import com.example.instagram.databinding.DialogCustomBinding
import com.example.instagram.databinding.FragmentRegisterPhotoBinding

class CustomDialog(context: Context) : Dialog(context) {
    // adicionar programaticamente cada texto novo do nosso dialog
    private lateinit var linearLayout: LinearLayout
    private lateinit var textButtons: Array<TextView>
    private var titleId:Int?=null
    private lateinit var txtdialog:TextView
    private lateinit var binding: DialogCustomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DialogCustomBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun setTitle(titleId: Int) {
        this.titleId=titleId
    }

    // vararg quer dizer que posso passar 1 ou varios dados como parametro
    fun addButton(vararg texts: Int, view: View.OnClickListener) {
        textButtons = Array(texts.size) {
            TextView(context)

        }
        texts.forEachIndexed { index, txt ->
            textButtons[index].id = txt
            textButtons[index].setText(txt)
            textButtons[index].setOnClickListener {
                view.onClick(it)
                // apos o clioque ele esconde
                dismiss()
            }
        }
    }

    override fun show() {
        super.show()
        titleId?.let {
            binding.dialogTitle.setText(it)
        }
        for (txtview in textButtons) {
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(30, 50, 30, 50)
            binding.linearContainer.addView(txtview, layoutParams)
        }
    }

}