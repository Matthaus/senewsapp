package br.com.matthaus.senewsapp.features.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.matthaus.senewsapp.models.TopHeadlineArticle
import br.com.matthaus.senewsapp.network.NewsOrgAPI
import br.com.matthaus.senewsapp.repositories.TopHeadlineArticleRepository

class NewsViewModel(val topHeadlineArticleRepository: TopHeadlineArticleRepository) : ViewModel() {

    private val topHeadlineArticles: MutableLiveData<List<TopHeadlineArticle>> by lazy {
        MutableLiveData<List<TopHeadlineArticle>>().also {
            loadTopHeadlineArticles()
        }
    }

    fun fetchHeadlineArticles(): MutableLiveData<List<TopHeadlineArticle>> {
        return topHeadlineArticles
    }

    private fun loadTopHeadlineArticles() {
        topHeadlineArticleRepository.fetchHeadlineArticles("se") {
            topHeadlineArticles.postValue(it)
        }
    }

    class NewsViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsViewModel(TopHeadlineArticleRepository(NewsOrgAPI.getInstance())) as T
        }
    }

}