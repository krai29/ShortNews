package com.krai29.shortnews.ui.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.krai29.shortnews.R
import com.krai29.shortnews.data.entity.Articles
import com.krai29.shortnews.data.entity.NewsResponse

//@Composable
//fun NewsList(response: NewsResponse, page: Int) {
//    LazyColumn{
//        items(response.articles){article ->
//            NormalTextComposable(textValue = article.title?:"NA")
//        }
//    }
//}

@Composable
fun NormalTextComposable(textValue: String) {
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.Black
        )
    )
}

@Composable
fun NewsTitleComposable(textValue: String) {
    Text(
        text = textValue,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(8.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium,
            color = Color.Black
        )
    )
}

@Composable
fun NewsItemComposable(page: Int, articles: Articles) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            //.padding(8.dp)
            .background(Color.White)
    ) {
        AsyncImage(
            model = articles.urlToImage,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp),
            contentScale = ContentScale.FillBounds,
            placeholder = painterResource(id = R.drawable.placeholder_image),
            error = painterResource(id = R.drawable.placeholder_image)
        )
        Spacer(modifier = Modifier.size(20.dp))

        NewsTitleComposable(textValue = articles.title ?: "")

        Spacer(modifier = Modifier.size(10.dp))

        NormalTextComposable(textValue = articles.description ?: "")

        Spacer(modifier = Modifier.weight(1f))

        AuthorDetailsComposable(articles.author, articles.source?.name)
    }
}

@Composable
fun AuthorDetailsComposable(authorName: String?, sourceName: String?) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    ) {
        authorName?.let { author ->
            Text(
                text = author,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )
        }
        sourceName?.also { source ->
            Text(
                text = source,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color.Black
                )
            )
        }
    }
}

@Composable
fun EmptyStateComposable(){
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    )
    {
        Image(
            modifier = Modifier.size(180.dp),
            painter = painterResource(id = R.drawable.ic_no_data),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(10.dp))
        NewsTitleComposable(textValue = stringResource(R.string.no_news_now_please_check_later))
    }
}

@Preview(showSystemUi = true)
@Composable
fun NewsItemComposablePreview() {
    val article = Articles(
        author = "Arnab Goswami",
        title = "India wants to know",
        description = "I want to know and I will shout and won't let panelist speak",
        url = null,
        urlToImage = null,
        publishedAt = null,
        content = null,
        source = null
    )
    NewsItemComposable(page = 0, articles = article)
}