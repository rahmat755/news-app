package com.arthur.newsapp.data.repository.sources

import androidx.room.Dao
import com.arthur.newsapp.data.datasource.local.SourcesDao
import com.arthur.newsapp.data.datasource.remote.NewsApi
import com.arthur.newsapp.data.model.source.Source
import com.arthur.newsapp.data.model.source.Sources
import kotlinx.coroutines.Deferred

class SourcesRepository constructor(private val dao: SourcesDao, private val api: NewsApi) :
    ISourcesRepository {
    override suspend fun getSourcesRemoteAsync(
        category: String,
        language: String,
        country: String
    ): Deferred<Sources> =
        api.getSources(category = category, language = language, country = country)

    override suspend fun getSourcesLocalByQueryAsync(
        query: String,
        category: String,
        country: String,
        language: String
    ): List<Source> = dao.select(query, category, country, language)

    override suspend fun getSourcesLocalAsync(): List<Source> = dao.select()
}