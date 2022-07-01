package com.example.newsapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.models.Article
import kotlinx.android.synthetic.main.item_article_preview.view.*

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.ArticleViewHolder>(){

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
        private val differCallback = object : DiffUtil.ItemCallback<Article>() {  ///////////DiffUtil?!!!!! calculate the update between two data(Articles) and keep them to not block the backGround Thread
            override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
                return oldItem == newItem
            }
        }

        val differ = AsyncListDiffer(this, differCallback) /////differ tool uses for comparing between two lists(Articles) and update the differences


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
       return ArticleViewHolder(
           LayoutInflater.from(parent.context).inflate(
               R.layout.item_article_preview,
               parent,
               false
           )
       )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
       val article = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(article.urlToImage).into(ivArticleImage)
            tvSource.text = article.source.name
            tvTitle.text = article.title
            tvDescription.text = article.description
            tvPublishedAt.text = article.publishedAt
            setOnClickListener {
                onItemClickListener?.let { it(article) }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnItemClickListener(listener : (Article) -> Unit){
        onItemClickListener = listener
    }

}