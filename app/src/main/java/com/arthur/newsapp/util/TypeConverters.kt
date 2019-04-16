package com.arthur.newsapp.util

import androidx.room.TypeConverter
import com.arthur.newsapp.data.model.news.Source
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TypeConverters {
    private val gson = Gson()

    @TypeConverter
    fun fromString(value: String?): List<String>? {
        val listType = object : TypeToken<ArrayList<String>>() {
        }.type
        return when (value) {
            null -> null
            else -> Gson().fromJson(value, listType)
        }
    }

    @TypeConverter
    fun fromArrayList(list: List<String>?): String? {
        return when (list) {
            null -> null
            else -> gson.toJson(list)

        }
    }

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