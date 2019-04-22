package com.arthur.newsapp.di

import com.arthur.newsapp.di.everything.EverythingComponent
import com.arthur.newsapp.di.everything.EverythingModule
import com.arthur.newsapp.di.top_articles.TopArticlesComponent
import com.arthur.newsapp.di.top_articles.TopArticlesModule
import com.arthur.newsapp.ui.dialogs.ChooseCountryDialogFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun plus(module: TopArticlesModule): TopArticlesComponent
    fun plus(module: EverythingModule): EverythingComponent
    fun inject(fr: ChooseCountryDialogFragment)
}