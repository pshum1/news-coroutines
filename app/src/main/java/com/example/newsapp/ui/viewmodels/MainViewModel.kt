package com.example.newsapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.repositories.NewsRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class MainViewModel : ViewModel() {

    private val newsRepository = NewsRepository()


    suspend fun getNewsDataAsync() = viewModelScope.async {
        newsRepository.getAllNews()
    }


}