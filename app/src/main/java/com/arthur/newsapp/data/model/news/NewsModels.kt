package com.arthur.newsapp.data.model.news

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arthur.newsapp.data.model.DataModel
import com.arthur.newsapp.data.model.ResponseModel
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class Response(
    override val status: String,
    val totalResults: Int,
    @SerializedName("articles")
    override val data: List<Article>
) : ResponseModel<Article>

@Parcelize
@Entity
data class Article(
    val source: Source,
    val author: String?,
    val title: String?,
    val description: String?,
    @PrimaryKey
    val url: String,
    val urlToImage: String?,
    val publishedAt: String
) : DataModel, Parcelable

@Parcelize
data class Source(val id: String?, val name: String): Parcelable