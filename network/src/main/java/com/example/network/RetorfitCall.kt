package com.example.network

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RetrofitCall<T:Any>(val proxy: Call<T>) : Call<NetworkResponse<T>> {
    override fun enqueue(callback: Callback<NetworkResponse<T>>) {
        proxy.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                val result = handleApi(response)
                callback.onResponse(this@RetrofitCall, Response.success(result))
            }

            override fun onFailure(call: Call<T>, t: Throwable) {

            }

        })

    }

    override fun clone(): Call<NetworkResponse<T>> {
        TODO("Not yet implemented")
    }

    override fun execute(): Response<NetworkResponse<T>> {
        TODO("Not yet implemented")
    }

    override fun isExecuted(): Boolean {
        TODO("Not yet implemented")
    }

    override fun cancel() {
        TODO("Not yet implemented")
    }

    override fun isCanceled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun request(): Request {
        TODO("Not yet implemented")
    }

    override fun timeout(): Timeout {
        TODO("Not yet implemented")
    }
}

fun <T:Any> handleApi(response: Response<T>): NetworkResponse<T> {

    return NetworkResponse.Success(data = response.body())
}