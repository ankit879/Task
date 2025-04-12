package com.example.ankittask.errorhandling
sealed class ResultState<out T>{

    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val exception: String) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}