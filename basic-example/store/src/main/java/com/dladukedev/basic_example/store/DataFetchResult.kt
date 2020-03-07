package com.dladukedev.basic_example.store

sealed class DataFetchResult {
    object Empty: DataFetchResult()
    object Loading: DataFetchResult()
    data class Error(val message: String): DataFetchResult()
    data class Content(val data: String): DataFetchResult()
}