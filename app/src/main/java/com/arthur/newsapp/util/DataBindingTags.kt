package com.arthur.newsapp.util

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.arthur.newsapp.R
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.signature.ObjectKey
import java.text.SimpleDateFormat
import java.util.*




@BindingAdapter("setTextOrHide")
fun setTextOrHide(view: TextView, text: String?) {
    if (text.isNullOrBlank()) {
        view.visibility = View.GONE
        return
    }
    view.text = text
}

@BindingAdapter("setTitle")
fun setTitle(view: TextView, text: String?) {
    if (text.isNullOrBlank()) {
        view.text = view.context.getString(R.string.app_name)
        return
    }
    view.text = text
}

@BindingAdapter("loadImg")
fun loadImg(view: ImageView, url: String) {
    GlideApp.with(view)
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.ALL)
        .error(R.drawable.ic_error_outline_black_24dp)
        .into(view)
}

@BindingAdapter("setDate")
fun setDate(view: TextView, dateString: String){
    val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
    val sdf1 = SimpleDateFormat("yyyy-MM-dd hh:mma")
    val date = sdf1.format(sdf.parse(dateString))
    view.text = date.toString()
}

