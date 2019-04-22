package com.arthur.newsapp.di.top_articles

import com.arthur.newsapp.ui.fragments.articles.top_articles_screen.TopArticlesFragment
import dagger.Subcomponent

@TopArticlesScope
@Subcomponent(modules = [TopArticlesModule::class])
interface TopArticlesComponent {
    fun inject(fr: TopArticlesFragment)
}