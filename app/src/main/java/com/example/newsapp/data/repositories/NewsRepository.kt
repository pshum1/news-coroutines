package com.example.newsapp.data.repositories

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.newsapp.data.models.Article
import com.example.newsapp.data.models.NewsResponse
import com.example.newsapp.data.networks.NewsAPI
import com.example.testingapplication.other.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val api: NewsAPI
) {

    suspend fun getAllNews() : Resource<NewsResponse> {
        Log.d("NEWSAPP", "YOU GOT TO API CALL")
        return try {
            val response = api.getNews()
            if (response.isSuccessful) {
                response.body().let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occurred", null)
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach server. Check your internet connection", null)
        }
    }

}