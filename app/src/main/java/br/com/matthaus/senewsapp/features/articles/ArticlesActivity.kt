package br.com.matthaus.senewsapp.features.articles

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.senewsapp.R
import br.com.matthaus.senewsapp.features.articles.adapters.TopHeadlineBannerAdapter
import br.com.matthaus.senewsapp.features.articles.adapters.TopHeadlineListAdapter
import br.com.matthaus.senewsapp.viewmodel.ArticlesViewModel
import kotlinx.android.synthetic.main.activity_articles.*
import kotlinx.android.synthetic.main.loader.*
import org.koin.android.ext.android.inject

const val SOURCE_ID_EXTRA = "SOURCE_ID_EXTRA"

class ArticlesActivity : AppCompatActivity() {

    val articlesViewModelFactory: ArticlesViewModel.NewsViewModelFactory by inject()

    private val articlesViewModel: ArticlesViewModel by lazy {
        ViewModelProviders.of(this, articlesViewModelFactory).get(ArticlesViewModel::class.java)
    }

    private val sourceId: String by lazy {
        intent.getStringExtra(SOURCE_ID_EXTRA)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_articles)

        setToolbar()
        setTopHeadlineArticlesBanners()
        setEverythingArticlesList()
    }

    private fun setToolbar() {
        collapsing_toolbar.apply {
            setExpandedTitleColor(Color.TRANSPARENT)
        }
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setDisplayShowHomeEnabled(true)
        }
    }

    private fun setTopHeadlineArticlesBanners() {
        articlesViewModel.fetchTopHeadlineArticles(sourceId).observe(this, Observer {
            var viewPagerAdapter = TopHeadlineBannerAdapter(this, it)
            top_headline_banners.adapter = viewPagerAdapter

            progress_loader.visibility = View.GONE
        })
    }

    private fun setEverythingArticlesList() {
        articlesViewModel.fetchEverythingArticles(sourceId).observe(this, Observer {
            var recyclerViewAdapter = TopHeadlineListAdapter(it)
            top_headline_list.adapter = recyclerViewAdapter
            top_headline_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            top_headline_list.setHasFixedSize(true)

            progress_loader.visibility = View.GONE
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
