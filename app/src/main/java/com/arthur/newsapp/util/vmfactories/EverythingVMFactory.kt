package com.arthur.newsapp.util.vmfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arthur.newsapp.domain.articles.everithing.IEverythingArticlesUseCase
import com.arthur.newsapp.ui.fragments.articles.everything_articles_screen.EverythingArticlesViewModel
import javax.inject.Inject

class EverythingVMFactory  @Inject constructor(private var useCase: IEverythingArticlesUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return  if (modelClass.isAssignableFrom(EverythingArticlesViewModel::class.java))
            EverythingArticlesViewModel(useCase) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}