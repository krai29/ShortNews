package com.krai29.shortnews.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.krai29.shortnews.data.AppConstants
import com.krai29.shortnews.data.entity.NewsResponse
import com.krai29.shortnews.ui.repositories.ShortNewsRepository
import com.krai29.utilities.ResourceState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ShortNewsViewModel @Inject constructor(
    private val shortNewsRepository: ShortNewsRepository
) : ViewModel() {

    private val _news : MutableStateFlow<ResourceState<NewsResponse>> =
        MutableStateFlow(ResourceState.Loading())

    val news : StateFlow<ResourceState<NewsResponse>> = _news

    init {
        getNews(AppConstants.COUNTRY )
    }
    private fun getNews(country: String) {
        viewModelScope.launch(Dispatchers.IO) {
            shortNewsRepository.getNewsHeadline(country)
                .collectLatest { newsResponse ->
                    _news.value = newsResponse
                }
        }
    }
}