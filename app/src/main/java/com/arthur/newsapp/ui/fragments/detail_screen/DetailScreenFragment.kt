package com.arthur.newsapp.ui.fragments.detail_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.databinding.DetailScreenFragmentBinding

class DetailScreenFragment : Fragment() {

    private lateinit var viewModel: DetailScreenViewModel

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
            binding.article = article
            binding.ivTransition = ivTransition
            binding.tvAuthorTrans = authorTransition
            binding.tvTitleTrans = titleTransition
            binding.tvDescTrans = descTransition
            binding.tvDateTrans =dateTransition
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(DetailScreenViewModel::class.java)
        // TODO: Use the ViewModel

    }

}
