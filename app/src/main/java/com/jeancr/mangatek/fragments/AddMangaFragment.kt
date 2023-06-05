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
import android.widget.TextView
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import com.jeancr.mangatek.MainActivity
import com.jeancr.mangatek.MangaModel
import com.jeancr.mangatek.MangaRepository
import com.jeancr.mangatek.MangaRepository.Singleton.downloadedUri
import com.jeancr.mangatek.R
import com.jeancr.mangatek.networking.ApiService
import com.jeancr.mangatek.networking.RetrofitHelper
import com.jeancr.mangatek.viewmodel.MainViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID

class AddMangaFragment(private val context:MainActivity): Fragment() {
    lateinit var textView : TextView
    lateinit var mainViewModel: MainViewModel
    lateinit var title :String
    lateinit var imageUrl: Uri
    private var file:Uri? =null
    //private var uploadedImage:ImageView?= null


    @OptIn(DelicateCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainViewModel= MainViewModel()
        textView = view.findViewById(R.id.test)






        val buttonManga = requireView().findViewById<Button>(R.id.confirm_button)
        buttonManga.setOnClickListener(View.OnClickListener() {
            val eanInput = view.findViewById<EditText>(R.id.ean_input)
            val codeEAN= eanInput.text.toString()
            //val data=MainViewModel().getMangaData("9782344057186")
            val quotesApi = RetrofitHelper.getInstance().create(ApiService::class.java)
            // launching a new coroutine
            GlobalScope.launch {
                val result = quotesApi.getManga("9171e2550dmsh7e7eb141e726fadp10fa8ejsnf5f44e61561b",codeEAN)
                //récupération des données du mangas
                title= result.body()?.product?.title.toString()
                imageUrl= result.body()?.product?.images?.get(0)?.toUri()!!

                val repo =MangaRepository()
                //repo.uploadImage(file!!){
                val downloadImageUrl=imageUrl

                //creer l'objet pour envoyer en bdd
                val manga= MangaModel(
                    UUID.randomUUID().toString(),
                    downloadImageUrl.toString(),
                    title
                )
                repo.insertManga(manga)
                // }
        }
0
        })

    }
/*
    private fun subscribe() {

        mainViewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            // Set the result text to Loading
            if (isLoading) textView.text = resources.getString(R.string.homecard_title)
        }

        mainViewModel.isError.observe(viewLifecycleOwner) { isError ->
            // Hide display image and set the result text to the error message
            if (isError) {
                textView.text = mainViewModel.errorMessage
            }
        }

        mainViewModel.mangaData.observe(viewLifecycleOwner) { mangaData ->
            // Display weather data to the UI
            textView.text = ("resultText")
            if (mangaData != null) {
                textView.text = ("resultText")
                setResultText(mangaData)

            }
        }
    }

    private fun setResultText(mangaData: MangaApiResponse) {
        val resultText = StringBuilder("Result:\n")

        mangaData.product.let { product ->
            resultText.append("title: ${product?.title}\n")
            textView.text = product?.title
        }
        println("ah")
        println(mangaData.product)
        println("ah")
        textView.text = (resultText)
    }
*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    title="oui"

    val view = inflater?.inflate(R.layout.fragment_add_manga, container,false)


        val confirmButton =view?.findViewById<Button>(R.id.confirm_button)
       //confirmButton?.setOnClickListener {sendForm(title)}
        return view
    }

    private fun sendForm(titre:String) {
        val repo =MangaRepository()
        //repo.uploadImage(file!!){
            val downloadImageUrl=downloadedUri

            //creer l'objet pour envoyer en bdd
            val manga= MangaModel(
                UUID.randomUUID().toString(),
                downloadImageUrl.toString(),
                titre
            )
            repo.insertManga(manga)
       // }


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

            val selectedImage = imageUrl



        }
    }
}

