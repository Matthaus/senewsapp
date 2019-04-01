package br.com.matthaus.senewsapp.features.home

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.senewsapp.R
import br.com.matthaus.senewsapp.features.adapters.TopHeadlineBannerAdapter
import br.com.matthaus.senewsapp.features.adapters.TopHeadlineListAdapter
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this, NewsViewModel.NewsViewModelFactory()).get(NewsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setToolbar()
        setTopHeadlineArticlesBanners()
        setEverythingArticlesList()
    }

    private fun setToolbar() {
        collapsing_toolbar.apply {
            setExpandedTitleColor(Color.TRANSPARENT)

        }
    }

    private fun setTopHeadlineArticlesBanners() {
        newsViewModel.fetchHeadlineArticles().observe(this, Observer {
            var viewPagerAdapter = TopHeadlineBannerAdapter(this, it)
            top_headline_banners.adapter = viewPagerAdapter
        })
    }

    private fun setEverythingArticlesList() {
        newsViewModel.fetchEverythingArticles().observe(this, Observer {
            var recyclerViewAdapter = TopHeadlineListAdapter(it)
            top_headline_list.adapter = recyclerViewAdapter
            top_headline_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            top_headline_list.setHasFixedSize(true)
        })
    }

}
