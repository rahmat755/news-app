package com.arthur.newsapp.domain.main_screen

import com.arthur.newsapp.data.model.news.Article

interface IMainScreenUseCase {
    suspend fun getTopNewsAsync(
        query: String = "",
        category: String = "",
        sources: String = "",
        page: Int,
        country: String = "ru",
        pageSize: Int =1
    ): List<Article>?

    suspend fun getEverythingAsync(
        query: String = "",
        sources: String = "",
        page: Int,
        language: String = "",
        pageSize: Int
    ): List<Article>?

//    suspend fun getSourcesAsync(
//        category: String = "",
//        language: String = "ru",
//        country: String = ""
//    ): Deferred<Response>
}