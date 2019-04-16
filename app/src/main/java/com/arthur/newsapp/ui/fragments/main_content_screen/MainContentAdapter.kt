package com.arthur.newsapp.ui.fragments.main_content_screen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.arthur.newsapp.R
import com.arthur.newsapp.data.model.news.Article
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
        items.forEach {
            if (!articles.contains(it)) {
                articles.add(it)
                notifyItemInserted(articles.size - 1)
            }
        }
    }

    fun clear() {
        articles.clear()
        notifyDataSetChanged()
    }

    fun filter(predicate: String) {
        val regex = Regex(predicate)
        val tmp = articles.filter { article ->
            (if (article.description != null)
                article.description.contains(regex)
            else false) ||
                    (if (article.author != null)
                        article.author.contains(regex)
                    else false) ||
                    (if (article.title != null)
                        article.title.contains(regex)
                    else false)
        }
        articles.clear()
        articles.addAll(tmp)
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