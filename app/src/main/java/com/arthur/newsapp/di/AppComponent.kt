package com.arthur.newsapp.di

import com.arthur.newsapp.di.main_content.MainContentComponent
import com.arthur.newsapp.di.main_content.MainContentModule
import com.arthur.newsapp.ui.dialogs.ChooseCountryDialogFragment
import com.arthur.newsapp.ui.dialogs.ChooseCategoryDialogFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun plus(module: MainContentModule): MainContentComponent
    fun inject(fr: ChooseCountryDialogFragment)
    fun inject(fr: ChooseCategoryDialogFragment)
}