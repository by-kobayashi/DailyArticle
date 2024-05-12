package com.dream.dailyarticle

import android.app.Application
import android.content.Context
import java.util.Locale


class DailyArticleApp : Application() {
    companion object {
        private var INSTANCE: DailyArticleApp? = null
        fun getInstance(): DailyArticleApp {
            var instance = INSTANCE
            if (instance == null) {
                instance = DailyArticleApp()
            }
            return instance
        }
    }

     fun getContext(): Context {
        return applicationContext
    }

    override fun onCreate() {
        super.onCreate()
    }
}