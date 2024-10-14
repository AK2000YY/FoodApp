package com.example.foodapp.domain.model

sealed class Response<out T> {
    data object Loading: Response<Nothing>()
    data class Success<T>(val data: T): Response<T>()
    data class Failure(val e: Exception): Response<Nothing>()
}