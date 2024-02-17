package com.krai29.shortnews.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.krai29.shortnews.data.entity.NewsResponse
import com.krai29.shortnews.ui.composable.EmptyStateComposable
import com.krai29.shortnews.ui.composable.NewsItemComposable
import com.krai29.shortnews.ui.composable.ProgressLoader
import com.krai29.shortnews.ui.viewmodel.ShortNewsViewModel
import com.krai29.utilities.ResourceState

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(
    shortNewsViewModel: ShortNewsViewModel = hiltViewModel()
) {
    val newsResponse by shortNewsViewModel.news.collectAsState()

    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) {
        100
    }
    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize(),
        pageSize = PageSize.Fill,
      //  pageSpacing = 8.dp
    ) { page: Int ->
        when (newsResponse) {
            is ResourceState.Loading -> {
                ProgressLoader()
            }

            is ResourceState.Error -> {
                val response = (newsResponse as ResourceState.Error).error
            }

            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success<NewsResponse>).data
                //NewsList(response, page)
                if (response.articles.isNotEmpty()){
                    NewsItemComposable(page, response.articles[page])
                }else{
                    EmptyStateComposable()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}