package com.arthur.newsapp.data.repository.news

import com.arthur.newsapp.data.datasource.remote.NewsApi
import com.arthur.newsapp.data.model.news.Response
import kotlinx.coroutines.Deferred

class NewsRepository constructor(private val api: NewsApi) : INewsRepository {

    override suspend fun getEverythingAsync(
        query: String,
        sources: String,
        page: Int,
        language: String,
        pageSize: Int
    ): Deferred<Response> = api.getEverything(
        query = query,
        sources = sources,
        page = page,
        language = language,
        pageSize = pageSize
    )

    override suspend fun getSourcesAsync(category: String, language: String, country: String): Deferred<Response> =
        api.getSources(category = category, language = language, country = country)

    override suspend fun getTopNewsAsync(
        query: String,
        category: String,
        sources: String,
        page: Int,
        country: String,
        pageSize: Int
    ): Deferred<Response> = api.getTopNews(
        query = query,
        category = category,
        sources = sources,
        page = page,
        country = country,
        pageSize = pageSize
    )
}