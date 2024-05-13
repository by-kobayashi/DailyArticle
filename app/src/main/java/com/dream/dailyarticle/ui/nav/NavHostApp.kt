package com.dream.dailyarticle.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dream.dailyarticle.ui.screen.HomeScreen
import com.dream.dailyarticle.ui.screen.MainScreen


/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: NavHostApp
 * <p>@date: 2024/5/9-0:13
 * <p>@emil: 2996943369@qq.com
 * <p>@desc: 导航navhostapp
 */
@Composable
fun NavHostApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = DailyArticleRoute.Home.route
    ) {
        composable(DailyArticleRoute.Home.route) {
            HomeScreen()
        }
        composable(DailyArticleRoute.Main.route) {
            MainScreen()
        }
    }
}

sealed class DailyArticleRoute(val route: String) {
    data object Home : DailyArticleRoute("home")
    data object Main : DailyArticleRoute("main")
}