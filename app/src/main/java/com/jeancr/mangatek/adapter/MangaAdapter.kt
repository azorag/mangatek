package com.jeancr.mangatek.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jeancr.mangatek.MainActivity
import com.jeancr.mangatek.MangaModel
import com.jeancr.mangatek.R
import org.w3c.dom.Text

class MangaAdapter(private val context: MainActivity, private val mangaList :List<MangaModel>, private val  layoutId: Int) : RecyclerView.Adapter<MangaAdapter.ViewHolder>(){

    class ViewHolder(view:View) : RecyclerView.ViewHolder(view){
        val mangaImage = view.findViewById<ImageView>(R.id.image_item)
        val mangaName:TextView? = view.findViewById<TextView>(R.id.name_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =LayoutInflater.from(parent.context).inflate(layoutId, parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentManga =mangaList[position]
        Glide.with(context).load(Uri.parse(currentManga.imageUrl)).into(holder.mangaImage)

        holder.mangaName?.text= currentManga.name


    }

    override fun getItemCount(): Int = mangaList.size
}