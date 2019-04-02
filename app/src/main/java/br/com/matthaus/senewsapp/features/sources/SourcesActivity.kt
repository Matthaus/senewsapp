package br.com.matthaus.senewsapp.features.sources

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.senewsapp.R
import br.com.matthaus.senewsapp.features.articles.ArticlesActivity
import br.com.matthaus.senewsapp.features.articles.SOURCE_ID_EXTRA
import br.com.matthaus.senewsapp.features.sources.adapters.SourcesListAdapter
import br.com.matthaus.senewsapp.viewmodel.SourcesViewModel
import kotlinx.android.synthetic.main.activity_sources.*
import kotlinx.android.synthetic.main.loader.*

class SourcesActivity : AppCompatActivity() {

    private val sourcesViewModel: SourcesViewModel by lazy {
        ViewModelProviders.of(this, SourcesViewModel.SourcesViewModelFactory()).get(SourcesViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sources)

        setSourcesList()
    }

    private fun setSourcesList() {
        sourcesViewModel.fetchSources().observe(this, Observer {
            var recyclerViewAdapter = SourcesListAdapter(it)
            recyclerViewAdapter.onClick = { sourceId ->
                Intent(this, ArticlesActivity::class.java)
                    .apply {
                        putExtra(SOURCE_ID_EXTRA, sourceId)
                    }
                    .also {
                        startActivity(it)
                    }
            }
            sources_list.adapter = recyclerViewAdapter
            sources_list.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
            sources_list.setHasFixedSize(true)

            progress_loader.visibility = View.GONE
        })
    }

}
