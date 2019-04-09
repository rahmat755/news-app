package com.arthur.newsapp.di.main_content

import dagger.Subcomponent

@MainModuleScope
@Subcomponent(modules = [MainContentModule::class])
interface MainContentComponent {
    fun inject(fr: MainContentFragment)
}