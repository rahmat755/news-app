package com.arthur.newsapp.util.vmfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arthur.newsapp.domain.articles.top_articles.ITopArticlesUseCase
import com.arthur.newsapp.ui.fragments.articles.top_articles_screen.TopArticlesViewModel
import javax.inject.Inject

class TopArticlesVMFactory  @Inject constructor(private var useCase: ITopArticlesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return  if (modelClass.isAssignableFrom(TopArticlesViewModel::class.java))
            TopArticlesViewModel(useCase) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}