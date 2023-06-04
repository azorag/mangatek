package com.jeancr.mangatek.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.beust.klaxon.Json
import com.beust.klaxon.JsonReader
import com.beust.klaxon.Klaxon
import com.jeancr.mangatek.MainActivity
import com.jeancr.mangatek.MangaRepository
import com.jeancr.mangatek.R
import com.squareup.moshi.JsonClass
import com.squareup.moshi.Moshi
import kotlinx.android.synthetic.main.activity_main.textView
import kotlinx.android.synthetic.main.fragment_add_manga.ean_input
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException
import java.io.StringReader

class AddMangaFragment(private val context:MainActivity): Fragment() {
    lateinit var textView : TextView
    private var uploadedImage:ImageView?= null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val eanInput = view.findViewById<EditText>(R.id.ean_input)
        val codeEAN= eanInput.text.toString()
        val openURL = Intent(android.content.Intent.ACTION_VIEW)
        openURL.data = Uri.parse("https://youtube.com/$codeEAN")


        textView = view.findViewById(R.id.test)
        val buttonManga = requireView().findViewById<Button>(R.id.confirm_button)
        buttonManga.setOnClickListener(View.OnClickListener() {

            addManga()
            //startActivity(openURL)
            //textView.text = eanInput.text.toString()
        })

    }
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
        val mangaName=view.findViewById<EditText>(R.id.ean_input).text.toString()
    }

    @JsonClass(generateAdapter = true)
    data class Gist(var files: Map<String, GistFile>?)

    @JsonClass(generateAdapter = true)
    data class GistFile(var content: String?)

    private val moshi = Moshi.Builder().build()
    private val gistJsonAdapter = moshi.adapter(Gist::class.java)

    private fun addManga() {
        val client = OkHttpClient()
        val request = okhttp3.Request.Builder()
            .url("https://barcodes1.p.rapidapi.com/?query=9782344057186")
            .get()
            .addHeader("X-RapidAPI-Key", "9171e2550dmsh7e7eb141e726fadp10fa8ejsnf5f44e61561b")
            .addHeader("X-RapidAPI-Host", "barcodes1.p.rapidapi.com")
            .build()

        val response = client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        println("$name: $value")
                    }
                    println(response.body!!.string())
                    val test = response.body!!
                    println(test.javaClass)
                    val klaxon= Klaxon()
                    //val parsed=klaxon.parseJsonObject(StringReader(response.body!!.string()))
                    //val data=Klaxon().parseJsonObject(StringReader(response.body!!.string()))
                }
            }
        })


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



            val repo = MangaRepository()
            repo.uploadImage(selectedImage!!)

        }
    }
}

