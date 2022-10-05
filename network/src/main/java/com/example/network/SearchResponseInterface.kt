package com.example.network

import com.example.network.searchModel.SearchResponse
import dagger.Provides
import dagger.Subcomponent
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import javax.inject.Singleton

@Singleton
interface SearchResponseInterface {
    @GET("name/{text}")
    suspend fun getCountries(@Path("text") text: String): Response<SearchResponse>
}