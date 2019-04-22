package com.arthur.newsapp.data.model.source

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.arthur.newsapp.data.model.DataModel
import com.arthur.newsapp.data.model.ResponseModel
import com.google.gson.annotations.SerializedName

data class Sources(override val status: String, @SerializedName("sources") override val data: List<Source>) : ResponseModel<Source>

@Entity
data class Source(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) : DataModel