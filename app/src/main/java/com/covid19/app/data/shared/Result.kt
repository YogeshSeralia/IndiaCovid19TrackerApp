package com.covid19.app.data.shared

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Loading(val isLoading: Boolean) : Result<Boolean>()
    data class Error<out T : Throwable>(val throwable: T) : Result<T>()
}