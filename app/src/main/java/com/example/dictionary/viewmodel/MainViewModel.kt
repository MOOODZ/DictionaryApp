package com.example.dictionary.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionary.apiManager.ApiService
import com.example.dictionary.apiManager.RetrofitInstance
import com.example.dictionary.apiManager.networkModel.Words
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel : ViewModel() {
    private var getWords: MutableLiveData<Words>
    init {
        getWords = MutableLiveData<Words>()
    }

    fun wordsListObserve(): MutableLiveData<Words> {

        getWords = MutableLiveData<Words>()
        return getWords

    }

    fun getWordsList() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retrofitInstance =
                    RetrofitInstance.getRetrofitInstance().create(ApiService::class.java)
                val response = retrofitInstance.getWords()
                getWords.postValue(response)
            } catch (ex: Exception) {

                Log.v("Errors", ex.message.toString() + "")


            }


        }


    }

}
