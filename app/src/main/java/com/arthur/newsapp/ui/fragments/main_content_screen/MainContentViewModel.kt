package com.arthur.newsapp.ui.fragments.main_content_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.domain.main_screen.IMainScreenUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainContentViewModel constructor(private val useCase: IMainScreenUseCase) : ViewModel() {

    private val _topArticles: MutableLiveData<List<Article>?> = MutableLiveData()
    val topArticles: LiveData<List<Article>?>
        get() = _topArticles
    private val _errorLiveData: MutableLiveData<String> = MutableLiveData()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData


    fun load(query: String = "", page: Int = 1, country: String = "ru") {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val res = useCase.getTopNewsAsync(pageSize = 10, page = page, query = query, country = country)
                if (!res.isNullOrEmpty())
                    _topArticles.postValue(res)
                else _errorLiveData.postValue("No news")
            }
        }
    }
}
