package com.arthur.newsapp.ui.fragments.articles.everything_articles_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.domain.articles.everithing.IEverythingArticlesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EverythingArticlesViewModel constructor(private val useCase: IEverythingArticlesUseCase): ViewModel() {
    private val _articles: MutableLiveData<List<Article>?> = MutableLiveData()
    val articles: LiveData<List<Article>?>
        get() = _articles
    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData


    fun load(query: String = "", page: Int = 1, language: String = "ru") {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val res = useCase.getEverythingAsync(pageSize = 10, page = page, query = query, language = language)
                if (!res.isNullOrEmpty())
                    _articles.postValue(res)
                else _errorLiveData.postValue("No news")
            }
        }
    }
}
