package com.jeancr.mangatek.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.jeancr.mangatek.MainActivity
import com.jeancr.mangatek.R

class AddMangaFragment(private val context:MainActivity): Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater?.inflate(R.layout.fragment_add_manga, container,false)


        val confirmButton =view?.findViewById<Button>(R.id.confirm_button)
        confirmButton?.setOnClickListener {sendForm(view)}
        return view
    }

    private fun sendForm(view: View) {
        val mangaName=view.findViewById<EditText>(R.id.name_input).text.toString()
    }

    private fun test(){
        val intent =Intent()
        intent.type = "image/"
        intent.action =Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"select Picture"),47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==47&& resultCode == Activity.RESULT_OK){
            if(data == null|| data.data==null)return
            val selectedImage = data.data

        }
    }
}