package com.arthur.newsapp.di.everything

import com.arthur.newsapp.data.datasource.local.NewsDao
import com.arthur.newsapp.data.datasource.remote.NewsApi
import com.arthur.newsapp.data.repository.news.INewsRepository
import com.arthur.newsapp.data.repository.news.NewsRepository
import com.arthur.newsapp.domain.articles.everithing.EverythingArticlesUseCase
import com.arthur.newsapp.domain.articles.everithing.IEverythingArticlesUseCase
import com.arthur.newsapp.domain.articles.top_articles.ITopArticlesUseCase
import com.arthur.newsapp.util.vmfactories.TopArticlesVMFactory
import dagger.Module
import dagger.Provides

@Module
class EverythingModule {
    @EverythingScope
    @Provides
    fun provideNewsRepository(api: NewsApi, dao: NewsDao): INewsRepository = NewsRepository(api, dao)

    @EverythingScope
    @Provides
    fun provideUseCase(repo: INewsRepository): IEverythingArticlesUseCase = EverythingArticlesUseCase(repo)

    @EverythingScope
    @Provides
    fun provideVMFactory(useCase: ITopArticlesUseCase): TopArticlesVMFactory = TopArticlesVMFactory(useCase)
}