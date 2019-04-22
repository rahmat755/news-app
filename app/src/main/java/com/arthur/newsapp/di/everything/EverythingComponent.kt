package com.arthur.newsapp.di.everything

import com.arthur.newsapp.ui.fragments.articles.everything_articles_screen.EverythingArticlesFragment
import dagger.Subcomponent

@EverythingScope
@Subcomponent(modules = [EverythingModule::class])
interface EverythingComponent {
    fun inject(fr: EverythingArticlesFragment)
}