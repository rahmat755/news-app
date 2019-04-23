package com.arthur.newsapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arthur.newsapp.R
import com.arthur.newsapp.ui.fragments.main_content_screen.MainContentFragment



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().replace(R.id.container, MainContentFragment()).commit()
    }

    override fun onBackPressed() {
        val f = supportFragmentManager.findFragmentById(R.id.container)
        if (f is MainContentFragment) {
            System.exit(0)
        } else {
            super.onBackPressed()
        }
    }
}
