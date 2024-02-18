package com.krai29.shortnews.ui.repositories

import com.krai29.shortnews.data.datasource.NewsDataSource
import com.krai29.shortnews.data.entity.NewsResponse
import com.krai29.utilities.ResourceState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ShortNewsRepository @Inject constructor(
    private val newsDataSource: NewsDataSource
) {

    suspend fun getNewsHeadline(country: String): Flow<ResourceState<NewsResponse>> {
        return flow {
            emit(ResourceState.Loading())
            val response = newsDataSource.getNewsHeadline(country)
            if (response.isSuccessful && response.body() != null) {
                emit(ResourceState.Success(response.body()!!))
            } else {
                emit(ResourceState.Error("Error encountered while fetching news"))
            }
        }.catch { error ->
            emit(ResourceState.Error(error.localizedMessage ?: "Some error in flow"))
        }
    }
}
