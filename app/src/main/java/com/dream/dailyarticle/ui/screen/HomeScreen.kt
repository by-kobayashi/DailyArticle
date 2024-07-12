package com.dream.dailyarticle.ui.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Air
import androidx.compose.material.icons.twotone.Refresh
import androidx.compose.material.icons.twotone.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dream.dailyarticle.R
import com.dream.dailyarticle.actions.HomeActions
import com.dream.dailyarticle.state.HomeState
import com.dream.dailyarticle.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val uiState = homeViewModel.uiState
    val scrollState = rememberScrollState()
    val context = LocalContext.current
    LaunchedEffect(Unit) {
        homeViewModel.dispatcher(HomeActions.LoadData)
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        imageVector = Icons.TwoTone.Air,
                        contentDescription = null
                    )
                },
                actions = {
                    IconButton(modifier = Modifier.padding(end = 15.dp),
                        onClick = {
                            homeViewModel.dispatcher(HomeActions.LoadData)
                        }) {
                        Icon(
                            imageVector = Icons.TwoTone.Refresh,
                            tint = Color.Black,
                            contentDescription = "refresh"
                        )
                    }
                    IconButton(modifier = Modifier.padding(end = 15.dp),
                        onClick = {
                            // 获取剪贴板管理器
                            val clipboardManager =
                                context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            // 创建剪贴板数据
                            val clipData = ClipData.newPlainText(
                                "DailyArticle",
                                "${(uiState as HomeState.Success).entity?.title}\n${uiState.entity?.author}\n${uiState.entity?.text}"
                            )
                            // 将数据放入剪贴板
                            clipboardManager.setPrimaryClip(clipData)
                            // 显示复制成功的提示
                            Toast.makeText(context, "已复制到剪贴板！", Toast.LENGTH_SHORT).show()
                        }) {
                        Icon(
                            imageVector = Icons.TwoTone.Share,
                            tint = Color.Black,
                            contentDescription = "share"
                        )
                    }

                },
                title = { Text(stringResource(R.string.app_name)) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )

            )
        }
    ) {
        when (uiState) {
            is HomeState.Error -> {
                Column(
                    modifier = Modifier
                        .padding(top = it.calculateTopPadding())
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    uiState.error?.let { error ->
                        Text(error)
                    }
                }
            }

            is HomeState.Loading -> {
                Column(
                    modifier = Modifier
                        .padding(top = it.calculateTopPadding())
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    CircularProgressIndicator(strokeCap = StrokeCap.Round)
                }
            }

            is HomeState.Success -> {
                SuccessLayout(scrollState, uiState, it.calculateTopPadding())
            }
        }
    }
}

@Composable
private fun SuccessLayout(
    scrollState: ScrollState,
    uiState: HomeState.Success,
    it: Dp
) {
    SelectionContainer {
        Column(
            modifier = Modifier
                .padding(top = it)
                .fillMaxSize()
                .verticalScroll(state = scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            uiState.entity?.title?.let {
                Text(
                    text = it,
                    fontWeight = FontWeight.Bold,
                    fontSize = 30.sp,
                )
            }
            uiState.entity?.author?.let {
                Text(
                    modifier = Modifier
                        .padding(vertical = 5.dp),
                    text = it,
                    fontWeight = FontWeight.Thin,
                    fontSize = 16.sp,
                )
            }
            Text(
                modifier = Modifier
                    .padding(15.dp),
                text = buildString { append(uiState.entity?.text) },
                fontWeight = FontWeight.Normal
            )
        }

    }
}

@Preview(name = "Home")
@Composable
private fun PreviewHome() {
    HomeScreen()
}