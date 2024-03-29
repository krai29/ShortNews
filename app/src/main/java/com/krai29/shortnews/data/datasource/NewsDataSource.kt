package com.krai29.shortnews.data.datasource

import com.krai29.shortnews.data.entity.NewsResponse
import retrofit2.Response

interface NewsDataSource {
    suspend fun getNewsHeadline(country: String) : Response<NewsResponse>
}