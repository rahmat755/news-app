package com.arthur.newsapp.domain.source

import com.arthur.newsapp.data.model.source.Source
import com.arthur.newsapp.data.model.source.Sources
import com.arthur.newsapp.data.repository.sources.ISourcesRepository
import kotlinx.coroutines.Deferred

class SourcesUseCase constructor(private val repository: ISourcesRepository) : ISourcesUseCase {
    override suspend fun getSourcesAsync(
        query: String,
        category: String,
        country: String,
        language: String
    ): List<Source>? {
        var local: List<Source>?
        val remote: List<Source>?
        var result: List<Source>? = arrayListOf()
        try {
            local = if (query.isNotBlank())
                repository.getSourcesLocalByQueryAsync(query, category, country, language)
            else {
                repository.getSourcesLocalAsync()
            }
        } catch (e: Throwable){
            local = null
        }
        return result
    }
}