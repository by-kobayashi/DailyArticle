package com.dream.dailyarticle.ui.screen

import android.text.Spanned
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.HtmlCompat
import androidx.core.text.buildSpannedString
import com.dream.dailyarticle.constants.DailyArticleUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

@Composable
fun Home(
    modifier: Modifier = Modifier
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
    LaunchedEffect(Unit) {
        withContext(Dispatchers.Default) {
            document = Jsoup.connect(DailyArticleUri.getInstance().getDailyUrl())
                .get()
        }
    }
    content = document?.select("div.news-left")
    title = content?.select("H1")?.text().toString()
    author = content?.select(".article-info")?.select("span").toString()
    text =HtmlCompat.fromHtml(content?.select(".text").toString(), HtmlCompat.FROM_HTML_MODE_LEGACY)
    Text(text = "Home${text}")


}

@Preview(name = "Home")
@Composable
private fun PreviewHome() {
    Home()
}