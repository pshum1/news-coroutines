package com.example.newsapp.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.models.NewsResponse
import com.example.newsapp.data.repositories.NewsRepository
import com.example.testingapplication.other.Resource
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel @ViewModelInject constructor(
    private val newsRepository: NewsRepository
) : ViewModel() {

    val newsResponse = MediatorLiveData<Resource<NewsResponse>>()


    fun getNewsData() = viewModelScope.launch {
        newsResponse.postValue(newsRepository.getAllNews())
    }


}