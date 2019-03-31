package br.com.matthaus.senewsapp.models

import java.util.*

data class TopHeadlineArticle(
    val source: ArticleSource,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImg: String,
    val publishedAt: Date,
    val content: String
) {
}