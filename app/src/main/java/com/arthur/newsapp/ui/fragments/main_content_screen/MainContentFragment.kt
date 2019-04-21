package com.arthur.newsapp.ui.fragments.main_content_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.Fade
import com.arthur.newsapp.NewsApp

import com.arthur.newsapp.di.main_content.MainContentModule
import com.arthur.newsapp.util.vmfactories.MainContentVMFactory
import kotlinx.android.synthetic.main.main_content_fragment.*
import timber.log.Timber
import javax.inject.Inject
import com.arthur.newsapp.R
import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.ui.fragments.SettingsFragment
import com.arthur.newsapp.ui.fragments.detail_screen.DetailScreenFragment
import com.arthur.newsapp.util.*
import kotlinx.android.synthetic.main.item_article.*
import kotlinx.android.synthetic.main.item_article.tv_author
import kotlinx.android.synthetic.main.item_article.tv_desc
import kotlinx.android.synthetic.main.item_article.tv_title


class MainContentFragment : Fragment(), OnArticleClick {

    @Inject
    lateinit var vmFactory: MainContentVMFactory
    private lateinit var viewModel: MainContentViewModel
    private lateinit var mAdapter: MainContentAdapter
    private var mQuery = ""
    private var page = 1
    private lateinit var lm: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NewsApp.daggerAppComponent.plus(MainContentModule()).inject(this)
        viewModel = ViewModelProviders.of(this, vmFactory).get(MainContentViewModel::class.java)
        if (savedInstanceState == null) viewModel.load(country = readString(getString(R.string.country_code)) ?: "ru")
        lm = LinearLayoutManager(context)
        mAdapter = MainContentAdapter(this)
        val listener = InfiniteScrollListener({
            page += 1
            viewModel.load(
                mQuery, page, readString(getString(R.string.country_code)) ?: "ru"
            )
        }, lm)
        val decoration = EqualSpacingItemDecoration(4, EqualSpacingItemDecoration.VERTICAL)

        pb_loading.show()
        tb_main.apply {
            inflateMenu(R.menu.toolbar_menu)
            title = context?.getString(R.string.app_name)
            menu.also { menu ->
                val searchItem = menu.findItem(R.id.action_search)
                val searchView = searchItem.actionView as SearchView
                val refreshAction = menu.findItem(R.id.action_refresh)
                val settingsAction = menu.findItem(R.id.action_settings)

                settingsAction.setOnMenuItemClickListener {
                    val settings = SettingsFragment()
                    activity?.supportFragmentManager
                        ?.beginTransaction()
                        ?.detach(this@MainContentFragment)
//                        ?.hide(this@MainContentFragment)
                        ?.replace(R.id.container, settings, settings.javaClass.simpleName)
                        ?.addToBackStack(settings.javaClass.simpleName)
                        ?.commit()
                    return@setOnMenuItemClickListener false
                }

                refreshAction.setOnMenuItemClickListener {
                    page = 1
                    viewModel.load(mQuery, page, readString(getString(R.string.country_code)) ?: "ru")
                    mAdapter.clear()
                    pb_loading.show()
                    lm.scrollToPosition(0)
                    return@setOnMenuItemClickListener false
                }

                searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        page = 1
                        viewModel.load(query, page, readString(getString(R.string.country_code)) ?: "ru")
                        mQuery = query
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        if (searchView.query.isEmpty()) {
                            page = 1
                            viewModel.load("", page, readString(getString(R.string.country_code)) ?: "ru")
                        }
                        mAdapter.filter(newText)
                        mQuery = newText
                        return false
                    }

                })
            }
        }

        rv_articles.apply {
            layoutManager = lm
            adapter = mAdapter
            addItemDecoration(decoration)
            addOnScrollListener(listener)
        }

        swipe_r_layout.setOnRefreshListener {
            page = 1
            viewModel.load(
                mQuery, page, readString(getString(R.string.country_code)) ?: "ru"
            )
        }

        viewModel.run {

            topArticles.observe(this@MainContentFragment, Observer {
                pb_loading.hide()
                swipe_r_layout.isRefreshing = false
                tv_no_data.visibility = View.GONE
                mAdapter.addItems(it)
                Timber.e(it.toString())
            })

            errorLiveData.observe(this@MainContentFragment, Observer {
                pb_loading.hide()
                tv_no_data.visibility = View.VISIBLE
                swipe_r_layout.isRefreshing = false
                tv_no_data.text = it
            })

        }
    }

    override fun onClick(item: Article, position: Int) {
        Timber.e(position.toString())
        val bundle = Bundle().apply {
            putParcelable("article", item)
            putString("iv_transition", "iv_transition$position")
            putString("tv_author", "author_transition$position")
            putString("tv_title", "title_transition$position")
            putString("tv_desc", "desc_transition$position")
            putString("tv_date", "date_transition$position")
        }
        val details = DetailScreenFragment().apply {
            sharedElementEnterTransition = DetailsTransition()
            enterTransition = Fade()
            sharedElementReturnTransition = DetailsTransition()
            arguments = bundle
        }
        exitTransition = Fade()

        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.addSharedElement(iv_content, "iv_transition$position")
            ?.addSharedElement(tv_author, "author_transition$position")
            ?.addSharedElement(tv_title, "title_transition$position")
            ?.addSharedElement(tv_desc, "desc_transition$position")
            ?.addSharedElement(tv_date, "date_transition$position")
            ?.hide(this)
            ?.add(R.id.container, details, details.javaClass.simpleName)
            ?.addToBackStack(details.javaClass.simpleName)
            ?.commit()
    }
}
