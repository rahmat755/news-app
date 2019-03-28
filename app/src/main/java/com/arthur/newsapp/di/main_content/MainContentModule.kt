package com.arthur.newsapp.di.main_content

import com.arthur.newsapp.data.datasource.remote.NewsApi
import com.arthur.newsapp.data.repository.news.INewsRepository
import com.arthur.newsapp.data.repository.news.NewsRepository
import com.arthur.newsapp.util.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class MainContentModule {
    @MainModuleScope
    @Provides
    fun provideNewsRepository(api: NewsApi): INewsRepository = NewsRepository(api)
}