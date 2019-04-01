package br.com.matthaus.senewsapp.network

import br.com.matthaus.senewsapp.models.TopHeadlineArticles
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsOrgAPI {

    @GET("top-headlines")
    fun getHeadlineArticles(@Query("country") country: String, @Query("pageSize") pageSize: Int): Call<TopHeadlineArticles>

    @GET("everything")
    fun getEverythingArticlesByKeyword(@Query("q") keyword: String, @Query("language") language: String, @Query("page") page: Int): Call<TopHeadlineArticles>

    companion object {
        fun getInstance(): NewsOrgAPI {

            var httpClient = OkHttpClient.Builder()
                .addInterceptor {
                    val request = it.request().newBuilder()
                        .addHeader("X-Api-Key", "9812096f70f64557aa1f7a5525eee940")
                        .build()

                    it.proceed(request)
                }
                .build()


            return Retrofit
                .Builder()
                .client(httpClient)
                .baseUrl("https://newsapi.org/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NewsOrgAPI::class.java)
        }
    }

}