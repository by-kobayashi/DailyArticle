package com.dream.dailyarticle.ui.screen

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dream.dailyarticle.ui.actions.HomeActions
import com.dream.dailyarticle.ui.state.HomeState
import com.dream.dailyarticle.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel()
) {
    val uiState = homeViewModel.uiState
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        homeViewModel.dispatcher(HomeActions.LoadData)
    }
    when (uiState) {
        is HomeState.Error -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                uiState.error?.let { Text(it) }
            }
        }

        is HomeState.Loading -> {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                CircularProgressIndicator()
            }
        }

        is HomeState.Success -> {
            SuccessLayout(scrollState, uiState)
        }
    }


}

@Composable
private fun SuccessLayout(
    scrollState: ScrollState,
    uiState: HomeState.Success
) {
    Column(
        modifier = Modifier.fillMaxSize()
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
                .padding(5.dp),
            text = "${uiState.entity?.text}",
            fontWeight = FontWeight.Normal
        )
    }
}

@Preview(name = "Home")
@Composable
private fun PreviewHome() {
    HomeScreen()
}