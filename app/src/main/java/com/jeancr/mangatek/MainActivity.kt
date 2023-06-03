package com.jeancr.mangatek


import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.jeancr.mangatek.fragments.CollectionFragment
import com.jeancr.mangatek.fragments.HomeFragment
import android.widget.Button
import com.jeancr.mangatek.fragments.AddMangaFragment
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Response
import java.io.IOException

import androidx.constraintlayout.widget.ConstraintLayout


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       setContentView(R.layout.activity_main)
        //charger la page d'accueil lors de l'ouverture de l'appli
        loadFragment(HomeFragment(this))

        //gestion des liens de la nav et du bouton d'ajout
        val collection = findViewById<ImageView>(R.id.collection)
        collection.setOnClickListener(View.OnClickListener() {
            loadFragment(CollectionFragment(this))
        })
        val home = findViewById<ConstraintLayout>(R.id.app_title)
        home.setOnClickListener(View.OnClickListener() {
            loadFragment(HomeFragment(this))
        })
        val addManga = findViewById<ImageView>(R.id.addManga)
        addManga.setOnClickListener(View.OnClickListener() {
            loadFragment(AddMangaFragment(this))
        })

    }
    private fun loadFragment(fragment: Fragment){
        val repo = MangaRepository()

        repo.updateData {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }


    private val client = OkHttpClient()

    private fun addManga(url:String) {
        val request = okhttp3.Request.Builder().url(url).build()

        client.newCall(request).enqueue(object :Callback{
            override fun onFailure(call: Call, e: IOException) {}
            override fun onResponse(call: Call, response: Response) {
                println(response.body!!.string())
            }
        })
    }

    }
