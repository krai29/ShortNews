package com.krai29.shortnews.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.krai29.shortnews.ui.viewmodel.ShortNewsViewModel

@Composable
fun HomeScreen(
    shortNewsViewModel: ShortNewsViewModel = hiltViewModel()
){
    Surface(
       modifier = Modifier.fillMaxSize()
    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen()
}