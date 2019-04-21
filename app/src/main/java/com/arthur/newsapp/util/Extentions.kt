package com.arthur.newsapp.util

import android.app.Activity
import android.content.Context
import androidx.fragment.app.Fragment

fun Fragment.saveStringToSP(key: String, value: String) {
    val prefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return
    with(prefs.edit()) {
        putString(key, value)
        apply()
    }
}

fun Fragment.readString(key: String, defValue: String = "ru"): String? {
    val prefs = activity?.getPreferences(Context.MODE_PRIVATE) ?: return null
    return with(prefs) {
        getString(key, defValue)
    }
}

fun Activity.readString(key: String, defValue: String): String? {
    val prefs = this.getPreferences(Context.MODE_PRIVATE) ?: return null
    return with(prefs) {
        getString(key, defValue)
    }
}