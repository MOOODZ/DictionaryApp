package com.example.dictionary.apiManager

import android.util.Log
import com.example.dictionary.apiManager.networkModel.Art
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


public class ApiManager {

        companion object{

            public fun getRetrofitInstance (): Retrofit{

                val okHttpClient: OkHttpClient = OkHttpClient.Builder()
                    .readTimeout(60, TimeUnit.SECONDS)
                    .connectTimeout(60, TimeUnit.SECONDS)
                    .build()

                return   Retrofit.Builder()
                    .baseUrl("http://yerbyer.com/dectionary/backend/api/v1/dic/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build()
            }
        }
}
