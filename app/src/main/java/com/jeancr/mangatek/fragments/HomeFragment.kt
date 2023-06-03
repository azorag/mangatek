package com.jeancr.mangatek.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jeancr.mangatek.MainActivity
import com.jeancr.mangatek.MangaModel
import com.jeancr.mangatek.MangaRepository.Singleton.mangaList
import com.jeancr.mangatek.R
import com.jeancr.mangatek.adapter.MangaAdapter
import com.jeancr.mangatek.adapter.MangaItemDecoration

class HomeFragment(private val context:MainActivity) : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_home,container,false)

        val horizontalRecyclerView = view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView?.adapter=MangaAdapter(context,mangaList, R.layout.item_horizontal_manga)

        val verticalRecyclerView = view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
       verticalRecyclerView?.adapter=MangaAdapter(context, mangaList, R.layout.item_vertical_manga)
        verticalRecyclerView?.addItemDecoration(MangaItemDecoration())

        return view
    }
}