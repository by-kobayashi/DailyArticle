package com.dream.dailyarticle.ui.screen

import android.text.Spanned
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dream.dailyarticle.constants.DailyArticleUri
import com.dream.dailyarticle.viewmodel.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    homeViewModel: HomeViewModel = viewModel()
) {
    var document by remember {
        mutableStateOf<Document?>(null)
    }
//    主要截取内容
    var content by remember {
        mutableStateOf<Elements?>(null)
    }
//    标题
    var title by remember {
        mutableStateOf("title")
    }
//    作者
    var author by remember {
        mutableStateOf("author")
    }
//    正文
    var text by remember {
        mutableStateOf<Spanned?>(null)
    }
    val scrollState = rememberScrollState()
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            document = Jsoup.connect(DailyArticleUri.getInstance().getDailyUrl())
                .get()
        }
    }
    content = document?.select("div.news-left")
    title = content?.select("H1")?.text().toString()
    author = content?.select(".article-info")?.select("span")?.text().toString()
    text =
        HtmlCompat.fromHtml(content?.select(".text").toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(state = scrollState),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
        )
        Text(
            modifier = Modifier
                .padding(vertical = 5.dp),
            text = author,
            fontWeight = FontWeight.Thin,
            fontSize = 16.sp,
        )
        Text(
            modifier = Modifier
                .padding(5.dp),
            text = "$text",
            fontWeight = FontWeight.Normal
        )
    }


}

@Preview(name = "Home")
@Composable
private fun PreviewHome() {
    HomeScreen()
}