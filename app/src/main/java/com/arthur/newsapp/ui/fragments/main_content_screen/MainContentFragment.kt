package com.arthur.newsapp.ui.fragments.main_content_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import com.arthur.newsapp.NewsApp

import com.arthur.newsapp.di.main_content.MainContentModule
import com.arthur.newsapp.util.injectViewModel
import com.arthur.newsapp.util.vmfactories.MainContentVMFactory
import kotlinx.android.synthetic.main.main_content_fragment.*
import timber.log.Timber
import javax.inject.Inject
import com.arthur.newsapp.R
import com.arthur.newsapp.util.EqualSpacingItemDecoration


class MainContentFragment : Fragment() {

    @Inject
    lateinit var vmFactory: MainContentVMFactory
    private lateinit var viewModel: MainContentViewModel
    lateinit var mAdapter: MainContentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NewsApp.daggerAppComponent.plus(MainContentModule()).inject(this)
        mAdapter = MainContentAdapter()
        tb_main.inflateMenu(R.menu.toolbar_menu)
        tb_main.menu.also {
            val searchItem = it.findItem(R.id.action_search)
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query ?: return false
                    if (query.isNullOrBlank()) viewModel.load("")
                    mAdapter.filter(query)
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText ?: return false
                    if (newText.isNullOrBlank()) viewModel.load("")
                    mAdapter.filter(newText)
                    return false
                }

            })
        }
        val decoration =EqualSpacingItemDecoration(4, EqualSpacingItemDecoration.VERTICAL)
        with(rv_articles) {
            adapter = mAdapter
            addItemDecoration(decoration)
        }
        viewModel = injectViewModel(vmFactory)
        viewModel.topArticles.observe(this, Observer {
            mAdapter.addItems(it)
            Timber.e(it.toString())
        })
    }

}
