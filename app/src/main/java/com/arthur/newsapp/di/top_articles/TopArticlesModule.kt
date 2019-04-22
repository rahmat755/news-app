package com.arthur.newsapp.di.top_articles

import com.arthur.newsapp.data.datasource.local.NewsDao
import com.arthur.newsapp.data.datasource.remote.NewsApi
import com.arthur.newsapp.data.repository.news.INewsRepository
import com.arthur.newsapp.data.repository.news.NewsRepository
import com.arthur.newsapp.domain.articles.top_articles.ITopArticlesUseCase
import com.arthur.newsapp.domain.articles.top_articles.TopArticlesUseCase
import com.arthur.newsapp.util.vmfactories.TopArticlesVMFactory
import dagger.Module
import dagger.Provides

@Module
class TopArticlesModule {
    @TopArticlesScope
    @Provides
    fun provideNewsRepository(api: NewsApi, dao: NewsDao): INewsRepository = NewsRepository(api, dao)

    @TopArticlesScope
    @Provides
    fun provideUseCase(repo: INewsRepository): ITopArticlesUseCase = TopArticlesUseCase(repo)

    @TopArticlesScope
    @Provides
    fun provideVMFactory(useCase: ITopArticlesUseCase): TopArticlesVMFactory = TopArticlesVMFactory(useCase)
}