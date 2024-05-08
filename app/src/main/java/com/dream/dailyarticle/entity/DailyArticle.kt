package com.dream.dailyarticle.entity

import android.text.Spanned
import org.jsoup.select.Elements

data class DailyArticle(
    var content: Elements,
    var title: String,
    var author: String,
    var text: Spanned
)
