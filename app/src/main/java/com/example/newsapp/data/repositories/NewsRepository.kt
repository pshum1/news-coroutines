package com.example.newsapp.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.NewsResponse
import com.example.newsapp.data.networks.NewsAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {

    suspend fun getAllNews(): LiveData<NewsResponse> {

        val api = NewsAPI()
        val newsResponse = MutableLiveData<NewsResponse>()

        val result = api.getNews()
        if(result.isSuccessful) {
            Log.d("responseNews", result.body().toString() )
            newsResponse.value = result.body()
        } else {
            Log.d("responseNews", "Error connecting to API" )
        }

        return newsResponse
    }
}