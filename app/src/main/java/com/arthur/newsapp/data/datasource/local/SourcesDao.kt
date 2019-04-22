package com.arthur.newsapp.data.datasource.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import com.arthur.newsapp.data.model.source.Source

@Dao
interface SourcesDao {
    @Query("SELECT * from source")
    suspend fun select(): List<Source>

    @Query("""
        SELECT * from source
         where (name like :query or description like :query)
          and category = :category
          and language = :language
          and country = :country
    """)
    suspend fun select(query: String, category: String, country: String, language: String): List<Source>

    @Delete
    suspend fun delete(el: Source)

    @Query("DELETE FROM article")
    suspend fun deleteAll()
}