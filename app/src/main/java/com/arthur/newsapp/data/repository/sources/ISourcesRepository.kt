package com.arthur.newsapp.data.repository.sources

import com.arthur.newsapp.data.model.source.Source
import com.arthur.newsapp.data.model.source.Sources
import kotlinx.coroutines.Deferred

interface ISourcesRepository {
    suspend fun getSourcesRemoteAsync(
        category: String = "",
        language: String = "ru",
        country: String = ""
    ): Deferred<Sources>

    suspend fun getSourcesLocalByQueryAsync(
        query: String,
        category: String,
        country: String,
        language: String
    ): List<Source>

    suspend fun getSourcesLocalAsync(): List<Source>
}