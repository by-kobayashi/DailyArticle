package com.dream.dailyarticle.constants

/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: DailyArticleUri
 * <p>@date: 2024/5/8-23:52
 * <p>@emil: 2996943369@qq.com
 * <p>@desc: 每日一文uri
 */
class DailyArticleUri private constructor() {
    companion object {
        private var INSTANCE: DailyArticleUri? = null
        fun getInstance(): DailyArticleUri {
            var instance = INSTANCE
            if (instance == null) {
                instance = DailyArticleUri()
            }
            return instance
        }
    }

    private val dailyUrl = "https://www.dushu.com/meiwen/random/"
    fun getDailyUrl(): String {
        return dailyUrl
    }
}