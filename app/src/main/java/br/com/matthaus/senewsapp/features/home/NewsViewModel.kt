package br.com.matthaus.senewsapp.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.matthaus.senewsapp.models.TopHeadlineArticle
import br.com.matthaus.senewsapp.network.NewsOrgAPI
import br.com.matthaus.senewsapp.repositories.TopHeadlineArticleRepository

const val DEFAULT_ARTICLE_COUNTRY = "se"
const val MAX_ARTICLE_BANNERS = 5

class NewsViewModel(val topHeadlineArticleRepository: TopHeadlineArticleRepository) : ViewModel() {

    private val topHeadlineArticles: MutableLiveData<List<TopHeadlineArticle>> by lazy {
        MutableLiveData<List<TopHeadlineArticle>>().also {
            loadTopHeadlineArticles()
        }
    }

    private val everythingArticles: MutableLiveData<List<TopHeadlineArticle>> by lazy {
        MutableLiveData<List<TopHeadlineArticle>>().also {
            loadEverythingArticles()
        }
    }

    fun fetchHeadlineArticles(): MutableLiveData<List<TopHeadlineArticle>> {
        return topHeadlineArticles
    }

    fun fetchEverythingArticles(): MutableLiveData<List<TopHeadlineArticle>> {
        return everythingArticles
    }

    private fun loadTopHeadlineArticles() {
        topHeadlineArticleRepository.fetchHeadlineArticles(DEFAULT_ARTICLE_COUNTRY, MAX_ARTICLE_BANNERS) {
            topHeadlineArticles.postValue(it)
        }
    }

    private fun loadEverythingArticles() {
        topHeadlineArticleRepository.fetchEverythingArticles("Sweden", "sv", 1) {
            everythingArticles.postValue(it)
        }
    }

    class NewsViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(TopHeadlineArticleRepository(NewsOrgAPI.getInstance())) as T
        }
    }

}