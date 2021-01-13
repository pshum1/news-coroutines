package com.example.newsapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()

    lateinit var binding: ActivityMainBinding
    lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()

        init()

    }

    private fun init() {
//        CoroutineScope(Dispatchers.IO).launch {
//            var result = viewModel.getNewsDataAsync().await()
//
//            list = result.value!!.articles
//            Log.d("newsResponse", result.value!!.articles.toString())
//
//            setData()
//
//        }
        viewModel.getNewsData()
        viewModel.newsResponse.observe(this, Observer {
            it.data?.articles?.let { it1 -> newsAdapter.submitList(it1) }
        })

    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        newsAdapter = NewsAdapter()
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }


}