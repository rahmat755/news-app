package com.arthur.newsapp.di

import android.app.Application
import android.content.Context
import arthur.com.data.datasource.remote.NewsApi
import arthur.com.data.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule constructor(private val app: Application){

    @Singleton
    @Provides
    fun provideContext() : Context = app.applicationContext

    @Singleton
    @Provides
    fun provideNewsService(): NewsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(NewsApi::class.java)
}