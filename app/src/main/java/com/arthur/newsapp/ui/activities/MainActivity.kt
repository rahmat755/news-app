package com.arthur.newsapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arthur.newsapp.R
import com.arthur.newsapp.ui.fragments.articles.everything_articles_screen.EverythingArticlesFragment
import com.arthur.newsapp.ui.fragments.articles.everything_articles_screen.EverythingArticlesViewModel
import com.arthur.newsapp.ui.fragments.articles.top_articles_screen.TopArticlesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, EverythingArticlesFragment()).commit()
    }
}
