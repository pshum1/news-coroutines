package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Adapter
import android.widget.LinearLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapp.R
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.data.models.Article
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.ui.viewmodels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    lateinit var newsAdapter: NewsAdapter
    lateinit var list: List<Article>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        setupRecyclerView()

        init()

    }

    private fun init() {
        CoroutineScope(Dispatchers.IO).launch {
            var result = viewModel.getNewsDataAsync().await()

            list = result.value!!.articles
            Log.d("newsResponse", result.value!!.articles.toString())

            setData()

        }


    }

    private fun setData() {
        CoroutineScope(Dispatchers.Main).launch {
            newsAdapter.setData(list)
        }
    }

    private fun setupRecyclerView() = binding.recyclerView.apply {
        list = arrayListOf()
        newsAdapter = NewsAdapter(list)
        adapter = newsAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)
    }


}