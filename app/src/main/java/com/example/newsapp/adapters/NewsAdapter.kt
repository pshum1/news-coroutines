package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.data.models.Article

import com.example.newsapp.databinding.ItemNewsViewBinding
import com.squareup.picasso.Picasso

class NewsAdapter(private var mList: List<Article>) : RecyclerView.Adapter<NewsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemNewsViewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(mList[position])
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun setData(newList: List<Article>) {
        mList = newList
        notifyDataSetChanged()
    }


    inner class MyViewHolder(private val binding: ItemNewsViewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.tvNewsTitle.text = article.title
            binding.tvNewsContent.text = article.content
            binding.tvNewsAuthor.text = article.author

            Picasso.get().load(article.urlToImage).placeholder(R.drawable.ic_baseline_arrow_downward_24).into(binding.img)

        }
    }

}