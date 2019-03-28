package com.arthur.newsapp.data.repository.news

import com.arthur.newsapp.data.datasource.remote.NewsApi

class NewsRepository constructor(private val api: NewsApi): INewsRepository {

}