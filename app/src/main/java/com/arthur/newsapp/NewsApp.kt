package com.arthur.newsapp

import android.app.Application
import com.arthur.newsapp.di.AppComponent
import com.arthur.newsapp.di.AppModule
import com.arthur.newsapp.di.DaggerAppComponent
import com.arthur.newsapp.util.TimberTree
import timber.log.Timber

class NewsApp : Application() {
    companion object {
        lateinit var daggerAppComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(TimberTree())
        daggerAppComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }
}