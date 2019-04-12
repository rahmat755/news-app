package com.arthur.newsapp.data.repository.news

import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.data.model.news.Response
import kotlinx.coroutines.Deferred

interface INewsRepository {
    suspend fun getTopNewsRemoteAsync(
        query: String = "",
        category: String = "",
        sources: String = "",
        page: Int,
        country: String = "ru",
        pageSize: Int
    ): Deferred<Response>

    suspend fun getEverythingAsync(
        query: String = "",
        sources: String = "",
        page: Int,
        language: String = "",
        pageSize: Int
    ): Deferred<Response>

    suspend fun getSourcesAsync(
        category: String = "",
        language: String = "ru",
        country: String = ""
    ): Deferred<Response>

    suspend fun getTopNewsLocalAsync(query: String = ""): List<Article>

    suspend fun saveArticle(article: Article)
}