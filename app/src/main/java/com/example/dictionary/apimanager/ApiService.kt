package com.example.dictionary.apimanager

import com.example.dictionary.apimanager.networkModel.Article
import com.example.dictionary.apimanager.networkModel.Words
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ApiService {
    @POST("getListarticle")
    suspend fun getWords(): Words

    @FormUrlEncoded
    @POST("detaile_article")
    suspend fun getArticle(
        @Field("ID") id: String?
    ): Article
}