package com.example.tv3.api

import retrofit2.Response

sealed class ResponseState<T>(val data : T? = null, val error : String? = null) {
    class Loading<T>  : ResponseState<T>()
    class Success<T>(data: T?  = null) : ResponseState<T>(data = data)
    class Error<T>(error :  String? = null) : ResponseState<T>(error = error)

}