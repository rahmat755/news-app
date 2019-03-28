package com.arthur.newsapp.data.model.source

import com.arthur.newsapp.data.model.news.Source

data class Sources(val status: String, val sources: ArrayList<Source>)

data class Source(val id: String,
                  val name: String,
                  val description: String,
                  val url: String,
                  val category: String,
                  val language: String,
                  val country: String)