package br.com.matthaus.senewsapp.network

import br.com.matthaus.senewsapp.BuildConfig
import br.com.matthaus.senewsapp.models.Sources
import br.com.matthaus.senewsapp.models.TopHeadlineArticles
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsOrgAPI {

    @GET("top-headlines")
    fun getHeadlineArticles(@Query("sources") sources: String, @Query("pageSize") pageSize: Int): Call<TopHeadlineArticles>

    @GET("everything")
    fun getEverythingArticlesByKeyword(@Query("sources") sources: String, @Query("page") page: Int): Call<TopHeadlineArticles>

    @GET("sources")
    fun getSources(@Query("language") language: String): Call<Sources>

    companion object {

        fun getInstance(okHttpClient: OkHttpClient): NewsOrgAPI {

            return Retrofit
                .Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsOrgAPI::class.java)
        }
    }

}