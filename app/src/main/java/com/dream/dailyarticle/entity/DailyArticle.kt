package com.dream.dailyarticle.entity

import android.text.Spanned
import kotlinx.coroutines.flow.SharedFlow

data class DailyArticle(
    var title: String = "title",
    var author: String = "author",
    var text: Spanned? = null
)
