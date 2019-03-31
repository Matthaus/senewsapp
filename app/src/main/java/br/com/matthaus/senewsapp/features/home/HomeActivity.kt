package br.com.matthaus.senewsapp.features.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import br.com.matthaus.senewsapp.R

class HomeActivity : AppCompatActivity() {

    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProviders.of(this, NewsViewModel.NewsViewModelFactory()).get(NewsViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        newsViewModel.fetchHeadlineArticles().observe(this, Observer {
            Log.d("SENewsApp", it.count().toString())
        })

//        val retrofit =
//            Retrofit
//                .Builder()
//                .baseUrl("https://newsapi.org/v2/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//        val service = retrofit.create(NewsOrgAPI::class.java)
//
//        service.getHeadlineArticles("ES")
//            .enqueue(object : Callback<List<TopHeadlineArticle>> {
//                override fun onFailure(call: Call<List<TopHeadlineArticle>>, t: Throwable) {
//                    Log.d("SENewsApp", "NOk")
//                }
//
//                override fun onResponse(
//                    call: Call<List<TopHeadlineArticle>>,
//                    response: Response<List<TopHeadlineArticle>>
//                ) {
//                    Log.d("SENewsApp", response.code().toString())
//                }
//            })

    }
}
