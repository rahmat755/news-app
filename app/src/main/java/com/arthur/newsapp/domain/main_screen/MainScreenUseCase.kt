package com.arthur.newsapp.domain.main_screen

import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.data.repository.news.INewsRepository
import com.arthur.newsapp.domain.BaseUseCase
import java.text.SimpleDateFormat

class MainScreenUseCase constructor(private val repository: INewsRepository) : IMainScreenUseCase,
    BaseUseCase<Article>() {

    private val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")

    override suspend fun getTopNewsAsync(
        query: String,
        category: String,
        sources: String,
        page: Int,
        country: String,
        pageSize: Int
    ): List<Article>? {
        var result: ArrayList<Article>? = arrayListOf()
        var local: List<Article>?
        val remote: List<Article>? = safeApiCall({
            repository.getTopNewsRemoteAsync(
                query,
                category,
                sources,
                page,
                country,
                pageSize
            )
        }, errorMessage = "Can't get top news")

        try {
            local = if (query.isNotBlank())
                repository.getTopNewsLocalAsyncByQuery("%$query%")
            else repository.getTopNewsLocalAsync()
        } catch (e: Throwable) {
            local = null
        }

        when {
            local != null -> {
                remote?.forEach {
                    if (!local.contains(it)) {
                        result?.add(it)
                        repository.saveArticle(it)
                    }
                }
                result?.addAll(local)
            }
            remote != null -> {
                result?.addAll(remote)
                remote.forEach {
                    repository.saveArticle(it)
                }
            }
            else -> result = null
        }

        result?.sortByDescending {
            formatter.parse(it.publishedAt)
        }

        return result
    }

    override suspend fun getEverythingAsync(
        query: String,
        sources: String,
        page: Int,
        language: String,
        pageSize: Int
    ): List<Article>? = safeApiCall({
        repository.getEverythingAsync(
            query,
            sources,
            page,
            language,
            pageSize
        )
    }, errorMessage = "Can't load everything")

//    override suspend fun getSourcesAsync(
//        category: String,
//        language: String,
//        country: String
//    ): Deferred<Response> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }

}