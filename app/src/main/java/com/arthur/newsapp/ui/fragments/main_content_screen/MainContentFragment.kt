package com.arthur.newsapp.ui.fragments.main_content_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.transition.Explode
import androidx.transition.Slide
import com.arthur.newsapp.NewsApp

import com.arthur.newsapp.di.main_content.MainContentModule
import com.arthur.newsapp.util.injectViewModel
import com.arthur.newsapp.util.vmfactories.MainContentVMFactory
import kotlinx.android.synthetic.main.main_content_fragment.*
import timber.log.Timber
import javax.inject.Inject
import com.arthur.newsapp.R
import com.arthur.newsapp.data.model.news.Article
import com.arthur.newsapp.ui.fragments.detail_screen.DetailScreenFragment
import com.arthur.newsapp.util.EqualSpacingItemDecoration
import com.arthur.newsapp.util.DetailsTransition
import com.arthur.newsapp.util.ReturnTransition
import kotlinx.android.synthetic.main.item_article.*
import kotlinx.android.synthetic.main.item_article.tv_author
import kotlinx.android.synthetic.main.item_article.tv_desc
import kotlinx.android.synthetic.main.item_article.tv_title


class MainContentFragment : Fragment(), OnArticleClick {

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
        mAdapter = MainContentAdapter(this)
        tb_main.inflateMenu(R.menu.toolbar_menu)
        tb_main.menu.also {
            val searchItem = it.findItem(R.id.action_search)
            val searchView = searchItem.actionView as SearchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    viewModel.load(query)
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    if (searchView.query.isEmpty())
                        viewModel.load("")
                    mAdapter.filter(newText)
                    return false
                }

            })
        }
        val decoration = EqualSpacingItemDecoration(4, EqualSpacingItemDecoration.VERTICAL)

        with(rv_articles) {
            adapter = mAdapter
            addItemDecoration(decoration)
        }

        viewModel = injectViewModel(vmFactory)
        viewModel.topArticles.observe(this, Observer {
            pb_loading.visibility = View.GONE
            tv_no_data.visibility = View.GONE
            mAdapter.clear()
            mAdapter.addItems(it)
            Timber.e(it.toString())
        })

        viewModel.errorLiveData.observe(this, Observer {
            pb_loading.visibility = View.GONE
            tv_no_data.visibility = View.VISIBLE
            tv_no_data.text = it
        })

    }

    override fun onClick(item: Article, position: Int) {
        Timber.e(position.toString())
        val details = DetailScreenFragment()
        details.sharedElementEnterTransition = DetailsTransition()
        details.enterTransition = Slide()
        exitTransition = Slide()
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
            ?.add(R.id.container, details, this.javaClass.simpleName)
            ?.hide(this)
            ?.addToBackStack(this.javaClass.simpleName)
            ?.commit()
    }
}
