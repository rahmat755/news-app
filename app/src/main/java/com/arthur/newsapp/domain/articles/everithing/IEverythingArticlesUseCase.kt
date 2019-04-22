package com.arthur.newsapp.domain.articles.everithing

import com.arthur.newsapp.data.model.news.Article

interface IEverythingArticlesUseCase {
    suspend fun getEverythingAsync(
        query: String = "",
        sources: String = "",
        page: Int,
        language: String = "",
        pageSize: Int
    ): List<Article>?
}