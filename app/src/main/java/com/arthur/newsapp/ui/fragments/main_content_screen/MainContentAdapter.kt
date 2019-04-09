package com.arthur.newsapp.ui.fragments.main_content_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arthur.newsapp.R
import arthur.com.data.model.news.Article
import com.arthur.newsapp.databinding.ItemArticleBinding

class MainContentAdapter : RecyclerView.Adapter<MainContentAdapter.ViewHolder>() {

    private val articles: ArrayList<Article> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ItemArticleBinding =
            DataBindingUtil.inflate(layoutInflater, R.layout.item_article, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articles[position])
    }

    fun addItems(items: List<Article>?) {
        items ?: return
        articles.addAll(items)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Article) {
            binding.article = item
            binding.executePendingBindings()
        }
    }
}