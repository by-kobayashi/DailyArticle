package com.dream.dailyarticle.viewmodel

import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.core.text.HtmlCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dream.dailyarticle.DailyArticleApp
import com.dream.dailyarticle.constants.DailyArticleUri
import com.dream.dailyarticle.entity.DailyArticle
import com.dream.dailyarticle.actions.HomeActions
import com.dream.dailyarticle.state.HomeState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
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
    /**
     * Represents the UI state of the Home screen, which can be Loading, Ready, or Error.
     */
    var uiState by mutableStateOf<HomeState>(HomeState.Loading)
        private set // This property is meant to be read-only outside of this class, hence the private setter.

    /**
     * A private MutableSharedFlow instance to manage the flow of daily article data within the class.
     */
    private val _entity = MutableSharedFlow<DailyArticle>()

    /**
     * A derived property that exposes _entity as a read-only SharedFlow outside of the class.
     */
    private var entity = _entity.asSharedFlow()

    /**
     * 处理分派给此模块的操作，触发相应的反应，如数据加载。
     *
     * @param actions 要处理的操作，目前仅支持 LoadData 操作。
     */
    fun dispatcher(actions: HomeActions) {
        when (actions) {
            HomeActions.LoadData -> {
                loadData() //在调度 LoadData 操作时启动数据加载。
            }
        }
    }
    /**
     * 初始化函数，用于启动一个协程来收集实体数据，并将成功获取的数据更新到UI状态中。
     * 这个函数没有参数和返回值，因为它是在类的初始化块中定义的，主要用于设置viewModel的相关状态。
     */
    init {
        // 在viewModel的作用域中启动一个新的协程
        viewModelScope.launch {
            // 收集entity的数据，每当有新的数据时，更新uiState为Success状态，并传入收集到的item
            entity.collect { item ->
                uiState = HomeState.Success(item)
            }
        }
    }

    /**
     * 加载数据的函数，用于从网络获取文章内容并更新UI。
     * 该函数使用了Coroutines进行异步处理，首先通过Jsoup连接到指定的URL获取HTML文档，
     * 然后从文档中提取文章的标题、作者和正文内容。
     * 如果获取数据过程中发生异常，会更新UI状态显示错误信息。
     *
     * @param 无
     * @return 无
     */
    private fun loadData() {
        var doc: Document? = null
        var content: Elements? = null
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                // 尝试连接到URL并获取HTML文档
                try {
                    doc = Jsoup.connect(DailyArticleUri.getInstance().getDailyUrl())
                        .get()
                    content = doc?.select("div.news-left")
                } catch (e: Exception) {
                    // 如果发生异常，更新UI状态显示错误信息
                    uiState = HomeState.Error(e.message)
                }
            }
            // 从文档中提取标题、作者和正文
            val title = content?.select("H1")?.text().toString()
            val author = content?.select(".article-info")?.select("span")?.text().toString()
            val text =
                HtmlCompat.fromHtml(
                    content?.select(".text").toString(),
                    HtmlCompat.FROM_HTML_MODE_LEGACY
                )
            if (doc == null) {
                // 如果文档为空，更新UI状态显示错误信息
                uiState = HomeState.Error("数据为空！")
            } else {
                // 发出包含标题、作者和正文的数据实体，用于更新UI
                _entity.emit(
                    DailyArticle(
                        title = title,
                        author = author,
                        text = text
                    )
                )
            }
        }
    }
}