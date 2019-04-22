package com.arthur.newsapp.domain.articles.top_articles

import com.arthur.newsapp.data.model.news.Article

interface ITopArticlesUseCase {
    suspend fun getTopNewsAsync(
        query: String = "",
        category: String = "",
        sources: String = "",
        page: Int,
        country: String = "ru",
        pageSize: Int =1
    ): List<Article>?
}