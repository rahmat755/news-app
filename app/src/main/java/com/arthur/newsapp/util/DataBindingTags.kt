package com.arthur.newsapp.util

import android.graphics.Bitmap
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.app.ActivityCompat.startPostponedEnterTransition
import androidx.databinding.BindingAdapter
import com.arthur.newsapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

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
        .asBitmap()
        .load(url)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
//        .listener(object : RequestListener<Bitmap> {
//            override fun onLoadFailed(
//                e: GlideException?,
//                model: Any?,
//                target: Target<Bitmap>?,
//                isFirstResource: Boolean
//            ): Boolean = false
//
//            override fun onResourceReady(
//                resource: Bitmap?,
//                model: Any?,
//                target: Target<Bitmap>?,
//                dataSource: DataSource?,
//                isFirstResource: Boolean
//            ): Boolean {
//                view.setImageBitmap(resource)
//                return true
//            }
//        })
        .error(android.R.drawable.stat_notify_error)
        .into(view)
}