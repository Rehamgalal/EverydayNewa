package com.example.everydaynewa.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.everydaynewa.R
import com.example.everydaynewa.model.Article
import com.example.everydaynewa.utils.OnClickListener
import kotlinx.android.synthetic.main.news_item.view.*

class NewsAdapter(val listener: OnClickListener): PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(DiffUtilCallBack()) {


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.news_item,parent,false)
        return NewsViewHolder(inflater,listener)
    }

    class NewsViewHolder(view: View, val listener: OnClickListener): RecyclerView.ViewHolder(view) {

        fun bind(article: Article) {
            Glide.with(itemView.context)
                .load(article.urlToImage)
                .placeholder(R.drawable.placeholder)
                .into(itemView.image_view)

            itemView.title.text = article.title
            itemView.source.text = article.source.name
            itemView.setOnClickListener{
                listener.onArticleCLicked(article)
            }
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.title == newItem.title
        }

    }
}