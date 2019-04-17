package com.arthur.newsapp.ui.fragments.main_content_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.app.ActivityCompat.invalidateOptionsMenu
import androidx.lifecycle.Observer
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
import com.arthur.newsapp.ui.fragments.detail_screen.DetailScreenFragment
import com.arthur.newsapp.util.*
import kotlinx.android.synthetic.main.detail_screen_fragment.view.*
import kotlinx.android.synthetic.main.item_article.*
import kotlinx.android.synthetic.main.item_article.tv_author
import kotlinx.android.synthetic.main.item_article.tv_desc
import kotlinx.android.synthetic.main.item_article.tv_title


class MainContentFragment : Fragment(), OnArticleClick {

    @Inject
    lateinit var vmFactory: MainContentVMFactory
    private lateinit var viewModel: MainContentViewModel
    lateinit var mAdapter: MainContentAdapter
    private var mQuery = ""
    private var page = 1
    lateinit var lm: LinearLayoutManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_content_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        NewsApp.daggerAppComponent.plus(MainContentModule()).inject(this)
        viewModel = injectViewModel(vmFactory)
        lm = LinearLayoutManager(context)
        mAdapter = MainContentAdapter(this)
        tb_main.inflateMenu(R.menu.toolbar_menu)
        pb_loading.show()
        tb_main.tv_toolbar.text = context?.getString(R.string.app_name)
        tb_main.menu.also { menu ->
            val searchItem = menu.findItem(R.id.action_search)
            val searchView = searchItem.actionView as SearchView
            val refreshAction = menu.findItem(R.id.action_refresh)
            refreshAction.setOnMenuItemClickListener {
                page = 1
                viewModel.load(mQuery, page)
                mAdapter.clear()
                pb_loading.show()
                lm.scrollToPosition(0)
                return@setOnMenuItemClickListener false
            }
            searchView.setOnSearchClickListener {
                tb_main.tv_toolbar.visibility = View.GONE
            }
            searchView.setOnCloseListener {
                tb_main.tv_toolbar.visibility = View.VISIBLE
                return@setOnCloseListener false
            }
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    page = 1
                    viewModel.load(query, page)
                    mQuery = query
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (searchView.query.isEmpty()) {
                        page = 1
                        viewModel.load("", page)
                    }
                    mAdapter.filter(newText)
                    mQuery = newText
                    return false
                }

            })
        }
        val decoration = EqualSpacingItemDecoration(4, EqualSpacingItemDecoration.VERTICAL)
        if (savedInstanceState == null) viewModel.load()
        val listener = InfiniteScrollListener({
            page += 1
            viewModel.load(
                mQuery, page
            )
        }, lm)
        rv_articles.addOnScrollListener(listener)

        with(rv_articles) {
            layoutManager = lm
            adapter = mAdapter
            addItemDecoration(decoration)
        }

        swipe_r_layout.setOnRefreshListener {
            page = 1
            viewModel.load(
                mQuery, page
            )
        }

        viewModel.topArticles.observe(this, Observer {
            pb_loading.hide()
            swipe_r_layout.isRefreshing = false
            tv_no_data.visibility = View.GONE
            mAdapter.addItems(it)
            Timber.e(it.toString())
        })

        viewModel.errorLiveData.observe(this, Observer {
            pb_loading.hide()
            tv_no_data.visibility = View.VISIBLE
            swipe_r_layout.isRefreshing = false
            tv_no_data.text = it
        })
    }

    override fun onClick(item: Article, position: Int) {
        Timber.e(position.toString())
        val details = DetailScreenFragment()
        details.sharedElementEnterTransition = DetailsTransition()
        details.enterTransition = Fade()
        exitTransition = Fade()
        details.sharedElementReturnTransition = ReturnTransition()
        val bundle = Bundle()
        bundle.putParcelable("article", item)
        bundle.putString("iv_transition", "iv_transition$position")
        bundle.putString("tv_author", "author_transition$position")
        bundle.putString("tv_title", "title_transition$position")
        bundle.putString("tv_desc", "desc_transition$position")
        bundle.putString("tv_date", "date_transition$position")
        details.arguments = bundle
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
