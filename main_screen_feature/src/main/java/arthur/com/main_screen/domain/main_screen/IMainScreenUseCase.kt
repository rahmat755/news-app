package arthur.com.main_screen.domain.main_screen

import arthur.com.data.model.DataModel
import arthur.com.data.model.news.Article
import arthur.com.data.model.news.Response

interface IMainScreenUseCase {
    suspend fun getTopNewsAsync(
        query: String = "",
        category: String = "",
        sources: String = "",
        page: Int,
        country: String = "ru",
        pageSize: Int
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