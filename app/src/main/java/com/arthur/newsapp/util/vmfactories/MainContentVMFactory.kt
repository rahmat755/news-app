package com.arthur.newsapp.util.vmfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arthur.newsapp.domain.main_screen.IMainScreenUseCase
import com.arthur.newsapp.ui.fragments.main_content_screen.MainContentViewModel
import javax.inject.Inject

class MainContentVMFactory  @Inject constructor(private var useCase: IMainScreenUseCase): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return  if (modelClass.isAssignableFrom(MainContentViewModel::class.java))
            MainContentViewModel(useCase) as T
        else throw IllegalArgumentException("ViewModel Not Found")
    }
}