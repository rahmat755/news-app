package arthur.com.main_screen.domain.main_screen

import arthur.com.data.model.news.Article
import arthur.com.data.repository.news.INewsRepository
import arthur.com.main_screen.domain.BaseUseCase

class MainScreenUseCase constructor(private val repository: INewsRepository) : IMainScreenUseCase,
    BaseUseCase<Article>() {
    override suspend fun getTopNewsAsync(
        query: String,
        category: String,
        sources: String,
        page: Int,
        country: String,
        pageSize: Int
    ): List<Article>? = safeApiCall({
        repository.getTopNewsAsync(
            query,
            category,
            sources,
            page,
            country,
            pageSize
        )
    }, errorMessage = "Can't get top news")

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