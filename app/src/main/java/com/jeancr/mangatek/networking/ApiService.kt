package com.jeancr.mangatek.networking

import com.jeancr.mangatek.model.MangaApiResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("/")
    fun getManga(
        @Query("rapidapi-key") key:String=ApiConfig.API_KEY,
        @Query("query") EAN:String
    ): Call<MangaApiResponse>
}