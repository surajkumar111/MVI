package com.example.network

import com.example.network.searchModel.SearchResponse
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Retrofit {
    private val baseUrl = "https://restcountries.com/v3.1/"

    @Singleton
    @Provides
    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()

    }

    @Singleton
    @Provides
    fun getRetrofitService(retrofit: Retrofit): SearchResponseInterface {
        val api = retrofit.create(SearchResponseInterface::class.java)
        return api
    }

}
