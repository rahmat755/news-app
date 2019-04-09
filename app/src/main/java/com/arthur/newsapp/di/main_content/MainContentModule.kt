package com.arthur.newsapp.di.main_content

import arthur.com.data.datasource.remote.NewsApi
import arthur.com.data.repository.news.INewsRepository
import arthur.com.data.repository.news.NewsRepository
import arthur.com.main_screen.domain.main_screen.IMainScreenUseCase
import arthur.com.main_screen.domain.main_screen.MainScreenUseCase
import arthur.com.main_screen.MainContentVMFactory
import dagger.Module
import dagger.Provides

@Module
class MainContentModule {
    @MainModuleScope
    @Provides
    fun provideNewsRepository(api: NewsApi): INewsRepository = NewsRepository(api)

    @MainModuleScope
    @Provides
    fun provideUseCase(repo: INewsRepository): IMainScreenUseCase = MainScreenUseCase(repo)

    @MainModuleScope
    @Provides
    fun provideVMFactory(useCase: IMainScreenUseCase): arthur.com.main_screen.MainContentVMFactory =
        arthur.com.main_screen.MainContentVMFactory(useCase)
}