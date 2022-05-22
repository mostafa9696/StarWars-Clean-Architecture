package com.example.starwarscharacters.models

sealed class DataResource<T>(
    val data: T? = null,
    val error: ErrorResponse? =null
) {
    class Success<T>(data: T?): DataResource<T>(data)

    class Error<T>(errorResponse: ErrorResponse?) : DataResource<T>(error = errorResponse)

    class Loading<T> : DataResource<T>()

}