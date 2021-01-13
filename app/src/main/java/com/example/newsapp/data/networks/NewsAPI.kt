package com.example.newsapp.data.networks

import com.example.newsapp.data.models.NewsResponse
import com.example.newsapp.other.Constants
import com.example.newsapp.other.Constants.API_KEY
import com.example.newsapp.other.Constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("v2/top-headlines")
    suspend fun getNews(
        @Query("country")
        countryCode: String = "us",
        @Query("category")
        category: String = "business",
        @Query("apiKey")
        apiKey: String = API_KEY
    ) : Response<NewsResponse>

    
}