package com.dream.dailyarticle.actions

/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: HomeActions
 * <p>@date: 2024/5/9-2:33
 * <p>@emil: 2996943369@qq.com
 * <p>@desc:
 */
sealed class HomeActions {
    data object LoadData : HomeActions()
}