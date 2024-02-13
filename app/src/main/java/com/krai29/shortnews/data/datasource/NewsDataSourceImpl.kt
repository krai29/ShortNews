package com.krai29.shortnews.data.datasource

import com.krai29.shortnews.data.api.ApiService
import com.krai29.shortnews.data.entity.NewsResponse
import retrofit2.Response
import javax.inject.Inject

class NewsDataSourceImpl @Inject constructor(
    private val apiService: ApiService
) : NewsDataSource{
    override fun getNewsHeadline(country: String): Response<NewsResponse> {
    return apiService.getNewsHeadline(country)
    }
}