package com.krai29.shortnews.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.krai29.shortnews.data.entity.NewsResponse
import com.krai29.shortnews.ui.composable.NewsList
import com.krai29.shortnews.ui.composable.ProgressLoader
import com.krai29.shortnews.ui.viewmodel.ShortNewsViewModel
import com.krai29.utilities.ResourceState

@Composable
fun HomeScreen(
    shortNewsViewModel: ShortNewsViewModel = hiltViewModel()
) {
    val newsResponse by shortNewsViewModel.news.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        when (newsResponse) {
            is ResourceState.Loading -> {
                ProgressLoader()
            }

            is ResourceState.Error -> {
                val response = (newsResponse as ResourceState.Error).error
            }

            is ResourceState.Success -> {
                val response = (newsResponse as ResourceState.Success<NewsResponse>).data
                NewsList(response)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}