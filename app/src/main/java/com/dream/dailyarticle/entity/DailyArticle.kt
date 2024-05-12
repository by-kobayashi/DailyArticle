package com.dream.dailyarticle.entity

import android.text.Spanned

data class DailyArticle(
    var title: String = "title",
    var author: String = "author",
    var text: Spanned? = null
)
