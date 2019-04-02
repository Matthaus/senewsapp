package br.com.matthaus.senewsapp.repositories

import br.com.matthaus.senewsapp.models.Source
import br.com.matthaus.senewsapp.models.Sources
import br.com.matthaus.senewsapp.network.NewsOrgAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class SourceRepository {

    private lateinit var newsOrgAPI: NewsOrgAPI

    constructor(newsOrgAPI: NewsOrgAPI) {
        this.newsOrgAPI = newsOrgAPI
    }

    fun fetchSources(language: String, body: (List<Source>) -> Unit) {
        newsOrgAPI.getSources(language).enqueue(object : Callback<Sources> {
            override fun onFailure(call: Call<Sources>, t: Throwable) {
                body(Collections.emptyList())
            }

            override fun onResponse(
                call: Call<Sources>,
                response: Response<Sources>
            ) {
                body(response.body()?.sources ?: Collections.emptyList())
            }
        })
    }

}