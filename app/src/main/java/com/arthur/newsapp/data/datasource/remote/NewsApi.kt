package com.arthur.newsapp.data.datasource.remote

import com.arthur.newsapp.data.model.news.Response
import com.arthur.newsapp.data.utils.API_KEY
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("/v2/top-headlines")
    fun getTopNews(
        @Query("q") query: String,
        @Query("category") category: String,
        @Query("sources") sources: String ,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("country") country: String,
        @Query("pageSize") pageSize: Int
    ): Deferred<Response>

    @GET("/v2/everything")
    fun getEverything(
        @Query("q") query: String ,
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("language") language: String,
        @Query("pageSize") pageSize: Int
    ): Deferred<Response>

    @GET("/v2/sources")
    fun getSources(
        @Query("apiKey") apiKey: String = API_KEY,
        @Query("category") category: String,
        @Query("language") language: String,
        @Query("country") country: String
    ): Deferred<Response>

}