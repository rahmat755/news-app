package com.arthur.newsapp.di.main_content

import com.arthur.newsapp.data.datasource.local.NewsDao
import com.arthur.newsapp.data.datasource.remote.NewsApi
import com.arthur.newsapp.data.repository.news.INewsRepository
import com.arthur.newsapp.data.repository.news.NewsRepository
import com.arthur.newsapp.domain.main_screen.IMainScreenUseCase
import com.arthur.newsapp.domain.main_screen.MainScreenUseCase
import com.arthur.newsapp.util.vmfactories.MainContentVMFactory
import dagger.Module
import dagger.Provides

@Module
class MainContentModule {
    @MainModuleScope
    @Provides
    fun provideNewsRepository(api: NewsApi, dao: NewsDao): INewsRepository = NewsRepository(api, dao)

    @MainModuleScope
    @Provides
    fun provideUseCase(repo: INewsRepository): IMainScreenUseCase = MainScreenUseCase(repo)

    @MainModuleScope
    @Provides
    fun provideVMFactory(useCase: IMainScreenUseCase): MainContentVMFactory = MainContentVMFactory(useCase)
}