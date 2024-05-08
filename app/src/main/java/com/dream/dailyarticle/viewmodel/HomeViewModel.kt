package com.dream.dailyarticle.viewmodel

import android.text.Spanned
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
    private var document: Document? = null

    //    主要截取内容
    private var content: Elements? = null

    //    标题
    private var title: String? = null

    //    作者
    private var author: String? = null

    //    正文
    private var text: Spanned? = null

    fun dispatcher(actions: HomeActions) {
        when (actions) {
            is HomeActions.LoadData -> {
                getData(DailyArticleUri.getInstance().getDailyUrl())
            }
        }
    }

    private fun getData(url: String) {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                document = Jsoup.connect(url).get()
            }

            content = document?.select("div.news-left")
            title = content?.select("H1")?.text().toString()
            author = content?.select(".article-info")?.select("span")?.text().toString()
            text =
                HtmlCompat.fromHtml(
                    content?.select(".text").toString(),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            uiState = if (content == null) {
                HomeState.Error("加载失败！")
            } else {
                val entity = DailyArticle(
                    content = content!!,
                    title = title!!,
                    author = author!!,
                    text = text!!
                )
                HomeState.Success(entity)
            }
        }
    }
}