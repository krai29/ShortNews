package com.krai29.shortnews.di

import com.krai29.shortnews.data.AppConstants.APP_BASE_URL
import com.krai29.shortnews.data.api.ApiService
import com.krai29.shortnews.data.datasource.NewsDataSource
import com.krai29.shortnews.data.datasource.NewsDataSourceImpl
import com.krai29.shortnews.ui.repositories.ShortNewsRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BASIC
        }
        val httpClient = OkHttpClient().newBuilder().apply {
            addInterceptor(httpLoggingInterceptor)
        }
        httpClient.readTimeout(60, TimeUnit.SECONDS)
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory()).build()

        return Retrofit.Builder()
            .baseUrl(APP_BASE_URL)
            .client(httpClient.build())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit) : ApiService{
        return retrofit.create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsDataSource(apiService: ApiService):NewsDataSource{
        return NewsDataSourceImpl(apiService)
    }

    @Provides
    @Singleton
    fun providesShortNewsRepository(newsDataSource: NewsDataSource):ShortNewsRepository{
        return ShortNewsRepository(newsDataSource)
    }
}