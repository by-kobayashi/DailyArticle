package com.dream.dailyarticle

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dream.dailyarticle.ui.theme.DailyArticleTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

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
    var test by remember { mutableStateOf<Document?>(null) }
    var offset by remember { mutableFloatStateOf(0f) }
    val state = rememberDraggableState {
        offset = offset.plus(it)
    }
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            test = Jsoup.connect("https://www.runoob.com/").get()
        }

    }
    Text(
        text = test?.body().toString(),
        modifier = modifier
            .draggable(state = state, orientation = Orientation.Vertical)
    )


}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DailyArticleTheme {
        Greeting("Android")
    }
}