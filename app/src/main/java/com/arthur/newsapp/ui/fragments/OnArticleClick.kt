package com.arthur.newsapp.ui.fragments

import com.arthur.newsapp.data.model.news.Article

interface OnArticleClick {
    fun onClick(item: Article, position: Int)
}