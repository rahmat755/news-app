package com.arthur.newsapp.ui.fragments.detail_screen

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arthur.newsapp.R

import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.databinding.DetailScreenFragmentBinding
import com.arthur.newsapp.ui.fragments.web_view_fragment.WebViewFragment
import kotlinx.android.synthetic.main.detail_screen_fragment.*

class DetailScreenFragment : Fragment() {

    private var article: Article? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val bundle = arguments
        val binding = DetailScreenFragmentBinding.inflate(inflater, container, false)
        if (bundle != null) {
            val ivTransition = bundle.getString("iv_transition")
            val authorTransition = bundle.getString("tv_author")
            val titleTransition = bundle.getString("tv_title")
            val descTransition = bundle.getString("tv_desc")
            val dateTransition = bundle.getString("tv_date")
            val article = bundle.getParcelable<Article>("article")
            this.article = article
            binding.article = article
            binding.ivTransition = ivTransition
            binding.tvAuthorTrans = authorTransition
            binding.tvTitleTrans = titleTransition
            binding.tvDescTrans = descTransition
            binding.tvDateTrans = dateTransition
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        tb_detail.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        if (article != null) {
            tb_detail.title = article?.title ?: context?.getString(R.string.app_name)
        } else
            tb_detail.title = context?.getString(R.string.app_name)
        tb_detail.setNavigationOnClickListener {
            activity?.supportFragmentManager
                ?.popBackStack()
        }
        btn_share.setOnClickListener {
            article?.let {
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(
                        Intent.EXTRA_TEXT,
                        "Interesting article: ${it.title ?: ""}\r\n${it.url}\r\nPowered via NewsApi"
                    )
                    type = "text/plain"
                }
                startActivity(sendIntent)
            }
        }
        btn_open.setOnClickListener {
            article?.let {
                val bundle = Bundle()
                bundle.putString("url", article?.url)
                val fr = WebViewFragment()
                fr.arguments = bundle
                activity?.supportFragmentManager
                    ?.beginTransaction()
                    ?.hide(this)
                    ?.add(R.id.container,fr, fr.javaClass.simpleName)
                    ?.addToBackStack(fr.javaClass.simpleName)
                    ?.commit()
            }
        }
    }

}
