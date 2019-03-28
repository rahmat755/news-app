package com.arthur.newsapp.ui.fragments.main_content_screen

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.arthur.newsapp.R
import com.arthur.newsapp.data.repository.news.INewsRepository
import javax.inject.Inject

class MainContentFragment : Fragment() {

    @Inject
    lateinit var repo: INewsRepository

    companion object {
        fun newInstance() = MainContentFragment()
    }

    private lateinit var viewModel: MainContentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainContentViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
