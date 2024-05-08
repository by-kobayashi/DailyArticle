package com.dream.dailyarticle.ui.state

import org.jsoup.select.Elements

/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: HomeState
 * <p>@date: 2024/5/9-2:34
 * <p>@emil: 2996943369@qq.com
 * <p>@desc: ui状态封装
 */
sealed class HomeState {
    data object Loading : HomeState()
    data class Success(val content: Elements?) : HomeState()
    data class Error(val error: String? = "加载失败！") : HomeState()
}