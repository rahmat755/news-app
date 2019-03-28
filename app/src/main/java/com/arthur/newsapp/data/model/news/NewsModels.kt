package com.arthur.newsapp.data.model.news

import com.arthur.newsapp.data.model.source.Source

data class Response(val status: String, val totalResults: Int, val articles: ArrayList<Article>)

data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String
)

data class Source(val id: String?, val name: String)