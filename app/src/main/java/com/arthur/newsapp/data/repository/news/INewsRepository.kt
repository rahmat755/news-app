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
        pageSize: Int = 1
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

    suspend fun getTopNewsLocalAsyncByQuery(query: String = ""): List<Article>

    suspend fun getTopNewsLocalAsync(): List<Article>

    suspend fun saveArticle(article: Article)

    suspend fun deleteAll()
}