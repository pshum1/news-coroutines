package com.example.newsapp.data.networks

import com.example.newsapp.data.models.NewsResponse
import com.example.newsapp.other.Constants
import com.example.newsapp.other.Constants.BASE_URL
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface NewsAPI {

    @GET("top-headlines?country=us&category=business&apiKey=37c1c2b484f44289b805af8eb8f21f7f")
    suspend fun getNews() : Response<NewsResponse>


    companion object {
        operator fun invoke(): NewsAPI {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsAPI::class.java)
        }
    }
}