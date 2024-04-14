package com.dream.dailyarticle.service

import com.dream.dailyarticle.entity.DailyArticleModule
import retrofit2.http.GET

/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: DailyArticleService
 * <p>@date: 2024/4/14-3:18
 * <p>@emil: 2996943369@qq.com
 * <p>@desc:
 */
interface DailyArticleService {
    @GET("/article")
    fun getArticle(): String

    companion object {
        fun createDailyArticleService(): DailyArticleService {
            return DailyArticleModule.createRetrofit(DailyArticleModule.createOkhttpClient())
                .create(DailyArticleService::class.java)
        }
    }
}