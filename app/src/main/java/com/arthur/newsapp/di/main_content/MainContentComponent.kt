package com.arthur.newsapp.di.main_content

import com.arthur.newsapp.ui.fragments.main_content_screen.MainContentFragment
import dagger.Subcomponent

@MainModuleScope
@Subcomponent(modules = [MainContentModule::class])
interface MainContentComponent {
    fun inject(fr: MainContentFragment)
}