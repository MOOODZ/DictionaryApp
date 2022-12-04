package com.example.dictionary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.apimanager.ApiService
import com.example.dictionary.apimanager.RetrofitInstance
import com.example.dictionary.apimanager.networkModel.Article
import com.example.dictionary.apimanager.networkModel.Words
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private var getWords: MutableLiveData<Words> = MutableLiveData<Words>()
    private var getArticles: MutableLiveData<Article> = MutableLiveData<Article>()

    fun wordsListObserve(): MutableLiveData<Words> {
        getWords = MutableLiveData<Words>()
        return getWords
    }

    fun articlesListObserve(): MutableLiveData<Article> {
        getArticles = MutableLiveData<Article>()
        return getArticles
    }

    fun getWordsList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retrofitInstance =
                    RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
                val response = retrofitInstance.getWords()
                getWords.postValue(response)
            } catch (ex: Exception) {
                Log.v("ListErrors", ex.message.toString() + "")
            }
        }
    }


    fun getArticlesList(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retrofitInstance =
                    RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
                val response = retrofitInstance.getArticle(id)
                getArticles.postValue(response)


            } catch (ex: Exception) {
                val retrofitInstance =
                    RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
                val failedResponse = retrofitInstance.getArticle(id)
                getArticles.postValue(failedResponse)
                Log.v("Errors", ex.message.toString() + "")

            }
        }


    }
}






