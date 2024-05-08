package com.dream.dailyarticle.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dream.dailyarticle.ui.screen.Home
import com.dream.dailyarticle.ui.screen.Main


/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: NavHostApp
 * <p>@date: 2024/5/9-0:13
 * <p>@emil: 2996943369@qq.com
 * <p>@desc:
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostApp() {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val navController = rememberNavController()
    BottomSheetScaffold(sheetContent = {
        Column {
            Text("content")
        }
    }, scaffoldState = bottomSheetState) {
        NavHost(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            navController = navController,
            startDestination = DailyArticleRoute.Home.route
        ) {
            composable(DailyArticleRoute.Home.route) {
                Home()
            }
            composable(DailyArticleRoute.Main.route) {
                Main()
            }
        }
    }


}


sealed class DailyArticleRoute(val route: String) {
    data object Home : DailyArticleRoute("home")
    data object Main : DailyArticleRoute("main")
}