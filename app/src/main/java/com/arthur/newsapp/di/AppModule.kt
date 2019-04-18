package com.arthur.newsapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.arthur.newsapp.data.datasource.local.NewsDB
import com.arthur.newsapp.data.datasource.remote.NewsApi
import com.arthur.newsapp.data.utils.BASE_URL
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule constructor(private val app: Application) {

    private val appDatabase = Room.databaseBuilder(app, NewsDB::class.java, "news_db").build()

    @Singleton
    @Provides
    fun provideContext(): Context = app.applicationContext

    @Singleton
    @Provides
    fun provideNewsService(): NewsApi = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()
        .create(NewsApi::class.java)

    @Singleton
    @Provides
    fun provideAppDatabase() = appDatabase

    @Singleton
    @Provides
    fun provideNewsDao(db: NewsDB) = db.newsDao()
}