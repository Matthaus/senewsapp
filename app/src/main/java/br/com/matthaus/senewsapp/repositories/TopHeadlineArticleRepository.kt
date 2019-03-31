package br.com.matthaus.senewsapp.repositories

import br.com.matthaus.senewsapp.models.TopHeadlineArticle
import br.com.matthaus.senewsapp.models.TopHeadlineArticles
import br.com.matthaus.senewsapp.network.NewsOrgAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class TopHeadlineArticleRepository {

    private lateinit var newsOrgAPI: NewsOrgAPI

    constructor(newsOrgAPI: NewsOrgAPI) {
        this.newsOrgAPI = newsOrgAPI
    }

    fun fetchHeadlineArticles(country: String, body: (List<TopHeadlineArticle>) -> Unit) {
        newsOrgAPI.getHeadlineArticles(country).enqueue(object : Callback<TopHeadlineArticles> {
            override fun onFailure(call: Call<TopHeadlineArticles>, t: Throwable) {
                body(Collections.emptyList())
            }

            override fun onResponse(
                call: Call<TopHeadlineArticles>,
                response: Response<TopHeadlineArticles>
            ) {
                body(response.body()?.articles ?: Collections.emptyList())
            }
        })
    }

}