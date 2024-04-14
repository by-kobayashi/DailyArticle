package com.dream.dailyarticle

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dream.dailyarticle.service.DailyArticleService
import com.dream.dailyarticle.ui.theme.DailyArticleTheme
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DailyArticleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val scope = remember { MainScope() }
    var test = ""
    LaunchedEffect(key1 = Unit) {
        scope.launch {
            val networkService = DailyArticleService.createDailyArticleService()
            val newsEntity = networkService.getArticle()
            test = newsEntity
        }
    }
    Text(
        text = test,
        modifier = modifier
    )
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DailyArticleTheme {
        Greeting("Android")
    }
}