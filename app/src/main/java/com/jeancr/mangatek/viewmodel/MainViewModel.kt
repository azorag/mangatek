package com.jeancr.mangatek.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeancr.mangatek.model.MangaApiResponse
import com.jeancr.mangatek.networking.ApiConfig
import retrofit2.Callback
import retrofit2.Call
import retrofit2.Response

class MainViewModel() :ViewModel() {

    private val _mangaData = MutableLiveData<MangaApiResponse?>()
    val mangaData: LiveData<MangaApiResponse?> get() = _mangaData

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _isError = MutableLiveData<Boolean>()
    val isError: LiveData<Boolean> get() = _isError

    var errorMessage: String = ""
        private set

    fun getMangaData(EAN: String) {

        _isLoading.value = true
        _isError.value = false

        val client = ApiConfig.getApiService().getManga(EAN = EAN)

        // Send API request using Retrofit
        client.enqueue(object : Callback<MangaApiResponse> {

            override fun onResponse(
                call: Call<MangaApiResponse>,
                response: Response<MangaApiResponse>
            ) {
                val responseBody :MangaApiResponse? = response.body()
                if (!response.isSuccessful || responseBody == null) {
                    onError("Data Processing Error")
                    return
                }

                _isLoading.value = false
                _mangaData.postValue(responseBody)
            }

            override fun onFailure(call: Call<MangaApiResponse>, t: Throwable) {
                onError(t.message)
                t.printStackTrace()
            }

        })
    }

    private fun onError(inputMessage: String?) {

        val message = if (inputMessage.isNullOrBlank() or inputMessage.isNullOrEmpty()) "Unknown Error"
        else inputMessage

        errorMessage = StringBuilder("ERROR: ")
            .append("$message some data may not displayed properly").toString()

        _isError.value = true
        _isLoading.value = false
    }

}
