package com.arthur.newsapp.data.datasource.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.arthur.newsapp.data.model.news.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
@TypeConverters(com.arthur.newsapp.util.TypeConverters::class)
abstract class NewsDB : RoomDatabase(){
    abstract fun newsDao(): NewsDao
}