package com.dream.dailyarticle.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dream.dailyarticle.constants.DailyArticleUri
import com.dream.dailyarticle.entity.DailyArticle
import com.dream.dailyarticle.ui.actions.HomeActions
import com.dream.dailyarticle.ui.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: HomeViewModel
 * <p>@date: 2024/5/9-2:33
 * <p>@emil: 2996943369@qq.com
 * <p>@desc:
 */
class HomeViewModel : ViewModel() {
    var uiState by mutableStateOf<HomeState>(HomeState.Loading)
        private set
    private var entity: DailyArticle? = null
    fun dispatcher(actions: HomeActions) {
        when (actions) {
            HomeActions.LoadData -> {
                loadData()
            }
        }
    }

    private fun loadData() {
        var content: Elements
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                content = fetchData(DailyArticleUri.getInstance().getDailyUrl())
            }
            val title = content.select("H1").text().toString()
            val author = content.select(".article-info").select("span").text().toString()
            val text =
                HtmlCompat.fromHtml(
                    content.select(".text").toString(),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            entity = (
                    DailyArticle(
                        title = title,
                        author = author,
                        text = text
                    )
                    )
            uiState = HomeState.Success(entity)

        }
    }

    private fun fetchData(url: String): Elements {
        val doc: Document = Jsoup.connect(url)
            .get()
        return doc.select("div.news-left")
    }

}