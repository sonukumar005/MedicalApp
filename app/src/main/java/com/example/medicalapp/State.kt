package com.example.medicalapp

sealed class State<out T>{
    data class Success<T>(val data: T): State<T>()
    data class Error<T>(val message: String): State<T>()
    object Loading: State<Nothing>()
}