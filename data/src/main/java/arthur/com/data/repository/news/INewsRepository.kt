package arthur.com.data.repository.news

import arthur.com.data.model.news.Response
import kotlinx.coroutines.Deferred

interface INewsRepository {
    suspend fun getTopNewsAsync(
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
}