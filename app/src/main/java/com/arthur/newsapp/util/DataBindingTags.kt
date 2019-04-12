package com.arthur.newsapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.arthur.newsapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("setTextOrHide")
fun setTextOrHide(view: TextView, text: String?) {
    if (text.isNullOrBlank()) {
        view.visibility = View.GONE
        return
    }
    view.text = text
}

@BindingAdapter("loadImg")
fun loadImg(view: ImageView, url: String) {
    GlideApp.with(view)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .placeholder(R.drawable.ic_launcher_background)
        .error(android.R.drawable.stat_notify_error)
        .into(view)
}