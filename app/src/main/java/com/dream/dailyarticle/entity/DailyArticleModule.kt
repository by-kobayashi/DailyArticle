package com.dream.dailyarticle.entity

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: DailyArticleModule
 * <p>@date: 2024/4/14-3:24
 * <p>@emil: 2996943369@qq.com
 * <p>@desc:
 */
object DailyArticleModule {
    /**
     * 创建一个Retrofit实例
     * @param okHttpClient OkHttpClient实例，用于网络请求配置
     * @return Retrofit实例，配置了基础URL和OKHttpClient客户端
     */
    fun createRetrofit(okHttpClient: OkHttpClient): Retrofit {
        // 使用Retrofit.Builder来构建Retrofit实例，设置HTTP客户端和基础URL
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://htwinkle.cn")
            .build()
    }
    fun createOkhttpClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10,TimeUnit.SECONDS)
            .build()
    }
}