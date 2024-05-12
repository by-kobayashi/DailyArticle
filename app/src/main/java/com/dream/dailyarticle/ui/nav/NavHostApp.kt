package com.dream.dailyarticle.ui.nav

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Air
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dream.dailyarticle.R
import com.dream.dailyarticle.ui.actions.HomeActions
import com.dream.dailyarticle.ui.screen.HomeScreen
import com.dream.dailyarticle.ui.screen.MainScreen
import com.dream.dailyarticle.viewmodel.HomeViewModel


/**
 * <p>@author: Kobayashi
 * <p>@project: DailyArticle
 * <p>@projectName: NavHostApp
 * <p>@date: 2024/5/9-0:13
 * <p>@emil: 2996943369@qq.com
 * <p>@desc: 导航navhostapp
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavHostApp(homeViewModel: HomeViewModel = viewModel()) {
    val bottomSheetState = rememberBottomSheetScaffoldState()
    val navController = rememberNavController()
    BottomSheetScaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.Air,
                        contentDescription = null
                    )
                },
                actions = {
                    IconButton(modifier = Modifier.padding(end = 15.dp),
                        onClick = {
                            homeViewModel.dispatcher(HomeActions.LoadData)
                        }) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null
                        )
                    }

                },
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )

            )
        },
        sheetContent = {
            Column(modifier = Modifier.width(IntrinsicSize.Max)) {
                TextButton(onClick = {}) {
                    Text("one")
                }
                TextButton(modifier = Modifier.wrapContentWidth(), onClick = {}) {
                    Text("two")

                }
            }
        }, scaffoldState = bottomSheetState
    ) {
        NavHost(
            modifier = Modifier.padding(bottom = it.calculateBottomPadding()),
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


}


sealed class DailyArticleRoute(val route: String) {
    data object Home : DailyArticleRoute("home")
    data object Main : DailyArticleRoute("main")
}