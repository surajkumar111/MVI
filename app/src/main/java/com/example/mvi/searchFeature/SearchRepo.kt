package com.example.mvi.searchFeature

import com.example.network.SearchResponseInterface
import com.example.network.searchModel.SearchResponse
import javax.inject.Inject


class SearchRepo
@Inject
constructor(private val searchResponseInterface: SearchResponseInterface) {

    suspend fun getCountries(text: String): SearchResponse? {

        return searchResponseInterface.getCountries(text).body()
//        searchResponseInterface.getCountries(text).enqueue(object : Callback<SearchResponse> {
//            override fun onResponse(
//                call: Call<SearchResponse>,
//                response: Response<SearchResponse>
//            ) {
//                mResponse.invoke(response.body())
//            }
//
//            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
//                mResponse.invoke(
//                    null
//                )
//            }
//
//        })
    }
}