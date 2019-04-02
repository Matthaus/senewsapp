package br.com.matthaus.senewsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.matthaus.senewsapp.models.ArticleSource
import br.com.matthaus.senewsapp.models.Source
import br.com.matthaus.senewsapp.network.NewsOrgAPI
import br.com.matthaus.senewsapp.repositories.SourceRepository

const val DEFAULT_SOURCE_LANGUAGE = "en"

class SourcesViewModel(val sourceRepository: SourceRepository) : ViewModel() {

    private val sources: MutableLiveData<List<Source>> by lazy {
        MutableLiveData<List<Source>>().also {
            loadSources()
        }
    }

    fun fetchSources(): MutableLiveData<List<Source>> {
        return sources
    }

    private fun loadSources() {
        sourceRepository.fetchSources(DEFAULT_SOURCE_LANGUAGE) {
            sources.postValue(it)
        }
    }

    class SourcesViewModelFactory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SourcesViewModel(SourceRepository(NewsOrgAPI.getInstance())) as T
        }
    }

}