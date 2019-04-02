package br.com.matthaus.senewsapp.features.articles.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import br.com.matthaus.senewsapp.R
import br.com.matthaus.senewsapp.models.TopHeadlineArticle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_headline_article_banner_item.view.*
import java.text.SimpleDateFormat

class TopHeadlineBannerAdapter(val context: Context, val data: List<TopHeadlineArticle>) : PagerAdapter() {

    private var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = data.count()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.top_headline_article_banner_item, container, false)
        Picasso.with(context).load(data[position].urlToImage).into(itemView.top_headline_banner_image)
        itemView.top_headline_banner_title.text = data[position].title

        var formatter = SimpleDateFormat("dd MMMM yyyy - HH:mm")
        itemView.top_headline_banner_date.text = formatter.format(data[position].publishedAt)

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}