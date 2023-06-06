package com.jeancr.mangatek.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.jeancr.mangatek.MainActivity
import com.jeancr.mangatek.MangaRepository
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireView().findViewById<ImageView>(R.id.image_item)
            ?.setOnClickListener(View.OnClickListener() {
                loadFragment(MangaSingleCollection(context), R.string.add_manga)
            })
    }
    private fun loadFragment(fragment: Fragment, string:Int){
        val repo = MangaRepository()

        requireView().findViewById<TextView>(R.id.page_title).text=resources.getString(string)

        repo.updateData {
            val transaction = parentFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}