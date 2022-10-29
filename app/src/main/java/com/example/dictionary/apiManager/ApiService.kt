package com.example.dictionary.apiManager

import com.example.dictionary.apiManager.networkModel.Words
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @FormUrlEncoded
    @POST("getListarticle")
    fun getWords(
        @Field("ID")ID:String?

    ): Call<List<Words>>

}