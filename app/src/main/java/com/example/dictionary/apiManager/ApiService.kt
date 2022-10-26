package com.example.dictionary.apiManager

import com.example.dictionary.apiManager.networkModel.Art
import retrofit2.Call
import retrofit2.http.POST

interface ApiService {

    @POST("getListarticle")
    fun getWords(



    ): Call<List<Art>>

}