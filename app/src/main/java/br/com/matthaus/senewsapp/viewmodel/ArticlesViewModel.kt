package br.com.matthaus.senewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.matthaus.senewsapp.models.TopHeadlineArticle
import br.com.matthaus.senewsapp.repositories.TopHeadlineArticleRepository

const val MAX_ARTICLE_BANNERS = 5

class ArticlesViewModel(val topHeadlineArticleRepository: TopHeadlineArticleRepository) : ViewModel() {

    private val topHeadlineArticles: MutableLiveData<List<TopHeadlineArticle>> =
        MutableLiveData<List<TopHeadlineArticle>>()

    private val everythingArticles: MutableLiveData<List<TopHeadlineArticle>> =
        MutableLiveData<List<TopHeadlineArticle>>()

    fun fetchTopHeadlineArticles(sourceId: String): MutableLiveData<List<TopHeadlineArticle>> {
        loadTopHeadlineArticles(sourceId)
        return topHeadlineArticles
    }

    fun fetchEverythingArticles(sourceId: String): MutableLiveData<List<TopHeadlineArticle>> {
        loadEverythingArticles(sourceId)
        return everythingArticles
    }

    private fun loadTopHeadlineArticles(sourceId: String) {
        topHeadlineArticleRepository.fetchHeadlineArticles(
            sourceId,
            MAX_ARTICLE_BANNERS
        ) {
            topHeadlineArticles.postValue(it)
        }
    }

    private fun loadEverythingArticles(sourceId: String) {
        topHeadlineArticleRepository.fetchEverythingArticles(sourceId, 1) {
            everythingArticles.postValue(it)
        }
    }

    class NewsViewModelFactory(val topHeadlineArticleRepository: TopHeadlineArticleRepository) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticlesViewModel(topHeadlineArticleRepository) as T
        }
    }

}