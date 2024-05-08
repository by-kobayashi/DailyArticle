package com.dream.dailyarticle.entity

import android.text.Spanned
import org.jsoup.select.Elements

data class DailyArticle(
    var content: Elements? = null,
    var title: String = "title",
    var author: String = "author",
    var text: Spanned? = null
)
