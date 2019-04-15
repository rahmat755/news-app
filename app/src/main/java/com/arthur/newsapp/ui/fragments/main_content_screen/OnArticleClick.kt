package com.arthur.newsapp.ui.fragments.main_content_screen

import com.arthur.newsapp.data.model.news.Article

interface OnArticleClick {
    fun onClick(item: Article)
}