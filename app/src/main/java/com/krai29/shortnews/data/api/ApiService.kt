package com.krai29.shortnews.data.api

import com.krai29.shortnews.data.entity.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("v2/top-headlines")
    fun getNewsHeadline(
       @Query("country") country: String,
       @Query("apiKey") apiKey: String = "API_KEY"
    ) : Response<NewsResponse>
}