package com.arthur.newsapp.data.model.source

import com.arthur.newsapp.data.model.DataModel
import com.arthur.newsapp.data.model.ResponseModel

data class Sources(override val status: String, override val data: List<Source>) : ResponseModel<Source>

data class Source(
    val id: String,
    val name: String,
    val description: String,
    val url: String,
    val category: String,
    val language: String,
    val country: String
) : DataModel