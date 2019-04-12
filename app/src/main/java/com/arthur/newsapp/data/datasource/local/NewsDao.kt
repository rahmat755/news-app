package com.arthur.newsapp.data.datasource.local

import androidx.room.*
import com.arthur.newsapp.data.model.news.Article

@Dao
interface NewsDao {
    @Query("SELECT * FROM article")
    suspend fun select(): List<Article>

    @Query("SELECT * FROM article WHERE title like :query or author like :query or description like :query")
    suspend fun select(query: String): List<Article>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(el: Article)

    @Delete
    suspend fun delete(el: Article)

    @Update
    suspend fun update(el: Article)
}