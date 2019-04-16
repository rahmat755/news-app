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

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _topArticles.postValue(useCase.getTopNewsAsync(pageSize = 10, page = 1))
            }
        }
    }

    fun load(query: String ="") {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                _topArticles.postValue(
                    useCase.getTopNewsAsync(
                        query = query,
                        pageSize = 10,
                        page = 1
                    )
                )
            }
        }
    }
}
