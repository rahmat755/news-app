package com.arthur.newsapp.domain.source

import com.arthur.newsapp.data.model.source.Source
import com.arthur.newsapp.data.model.source.Sources
import kotlinx.coroutines.Deferred

interface ISourcesUseCase {
    suspend fun getSourcesAsync(
        query: String = "",
        category: String = "",
        country: String = "",
        language: String = "ru"
    ): List<Source>?
}