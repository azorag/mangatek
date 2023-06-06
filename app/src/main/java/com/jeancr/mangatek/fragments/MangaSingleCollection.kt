package com.jeancr.mangatek.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jeancr.mangatek.MainActivity
import com.jeancr.mangatek.MangaRepository
import com.jeancr.mangatek.R
import com.jeancr.mangatek.adapter.GridMangaItemDecoration
import com.jeancr.mangatek.adapter.MangaAdapter

class MangaSingleCollection(private val context: MainActivity) : Fragment(){





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.manga_single_collection,container,false)


        val gridView = view?.findViewById<RecyclerView>(R.id.manga_grid_collection)
        gridView?.adapter= MangaAdapter(context, MangaRepository.Singleton.mangaList, R.layout.single_manga)
        gridView?.layoutManager = GridLayoutManager(context,2)
        gridView?.addItemDecoration(GridMangaItemDecoration())

        return view

    }

}