package br.com.matthaus.senewsapp.features.articles.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.matthaus.senewsapp.R
import br.com.matthaus.senewsapp.models.TopHeadlineArticle
import br.com.matthaus.senewsapp.utils.DateUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_headline_article_list_item.view.*

class TopHeadlineListAdapter(private val data: List<TopHeadlineArticle>) :
    RecyclerView.Adapter<TopHeadlineListAdapter.TopHeadlineListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopHeadlineListViewHolder =
        LayoutInflater.from(parent.context).inflate(
            R.layout.top_headline_article_list_item,
            parent,
            false
        ).let { TopHeadlineListViewHolder(it) }


    override fun getItemCount(): Int = data.count()

    override fun onBindViewHolder(holder: TopHeadlineListViewHolder, position: Int) = holder.bind(data[position])

    class TopHeadlineListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val context = itemView.context
        val title = itemView.top_headline_list_item_title
        val date = itemView.top_headline_list_item_date
        val image = itemView.top_headline_list_item_image
        val author = itemView.top_headline_list_item_author

        fun bind(topHeadlineArticle: TopHeadlineArticle) {


            title.text = topHeadlineArticle.title
            date.text = DateUtils.formatDatetoString(topHeadlineArticle.publishedAt)
            author.text = topHeadlineArticle.author

            Picasso
                .with(context)
                .load(topHeadlineArticle.urlToImage)
                .placeholder(R.drawable.no_photo_available)
                .error(R.drawable.no_photo_available)
                .into(image)
        }

    }

}