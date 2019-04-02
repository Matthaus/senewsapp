package br.com.matthaus.senewsapp.features.articles.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import br.com.matthaus.senewsapp.R
import br.com.matthaus.senewsapp.models.TopHeadlineArticle
import br.com.matthaus.senewsapp.utils.DateUtils
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.top_headline_article_banner_item.view.*

class TopHeadlineBannerAdapter(val context: Context, val data: List<TopHeadlineArticle>) : PagerAdapter() {

    private var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun getCount(): Int = data.count()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val itemView = layoutInflater.inflate(R.layout.top_headline_article_banner_item, container, false)

        with(data[position]) {
            Picasso.with(context).load(urlToImage).into(itemView.top_headline_banner_image)
            itemView.top_headline_banner_title.text = title
            itemView.top_headline_banner_date.text = DateUtils.formatDatetoString(publishedAt)
            itemView.top_headline_banner_author.text = author
        }

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}