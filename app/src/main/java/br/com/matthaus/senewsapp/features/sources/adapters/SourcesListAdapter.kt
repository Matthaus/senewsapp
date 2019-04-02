package br.com.matthaus.senewsapp.features.sources.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.senewsapp.R
import br.com.matthaus.senewsapp.models.Source
import kotlinx.android.synthetic.main.article_source_list_item.view.*

class SourcesListAdapter(private val data: List<Source>) :
    RecyclerView.Adapter<SourcesListAdapter.SourcesListAdapterViewHolder>() {


    var onClick: (String) -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesListAdapterViewHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.article_source_list_item,
            parent,
            false
        ).let { SourcesListAdapterViewHolder(it, onClick) }

    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: SourcesListAdapterViewHolder, position: Int) = holder.bind(data[position])

    class SourcesListAdapterViewHolder(itemView: View, val onClick: (String) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        val name = itemView.article_source_list_name
        val description = itemView.article_source_list_description
        val url = itemView.article_source_list_url

        fun bind(articleSource: Source) {

            itemView.setOnClickListener {
                onClick(articleSource.id)
            }

            name.text = articleSource.name
            description.text = articleSource.description
            url.text = articleSource.url
        }


    }

}