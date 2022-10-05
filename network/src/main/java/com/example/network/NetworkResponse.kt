package com.example.network

sealed class NetworkResponse<T : Any> {
    data class Success<T : Any>(val data: Any?) : NetworkResponse<T>()
    data class Failure<T:Any>(val error: Any) : NetworkResponse<T>()
}