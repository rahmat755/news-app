package com.arthur.newsapp.ui.fragments.web_view_fragment


import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment

import com.arthur.newsapp.R
import kotlinx.android.synthetic.main.fragment_web_view.*

class WebViewFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_web_view, container, false)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val url = arguments?.getString("url")
        tb_webview.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
            setNavigationOnClickListener {
                activity?.supportFragmentManager
                    ?.popBackStack()
            }
        }
        wv_chrome.run {
            webChromeClient = WebChromeClient()
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl(url)
        }
    }
}