package com.arthur.newsapp.data.model.news

import com.arthur.newsapp.data.model.DataModel
import com.arthur.newsapp.data.model.ResponseModel
import com.arthur.newsapp.data.model.source.Source
import com.google.gson.annotations.SerializedName

data class Response(
    override val status: String,
    val totalResults: Int,
    @SerializedName("articles")
    override val data: List<Article>
) : ResponseModel<Article>

data class Article(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String
) : DataModel

data class Source(val id: String?, val name: String)