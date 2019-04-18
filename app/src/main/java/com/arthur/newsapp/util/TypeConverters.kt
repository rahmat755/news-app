package com.arthur.newsapp.util

import androidx.room.TypeConverter
import com.arthur.newsapp.data.model.news.Source
import com.google.gson.Gson

class TypeConverters {
    private val gson = Gson()
    @TypeConverter
    fun fromSource(source: Source?): String?{
        return when(source){
            null->null
            else-> gson.toJson(source)
        }
    }

    @TypeConverter
    fun toSource(json: String?): Source?{
        return when(json){
            null->null
            else-> gson.fromJson(json, Source::class.java)
        }
    }
}